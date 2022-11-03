package com.tispunshahryar960103.bookreader.screens.update

import android.annotation.SuppressLint
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.tispunshahryar960103.bookreader.R
import com.tispunshahryar960103.bookreader.components.*
import com.tispunshahryar960103.bookreader.data.DataOrException
import com.tispunshahryar960103.bookreader.model.MBook
import com.tispunshahryar960103.bookreader.navigation.ReaderScreens
import com.tispunshahryar960103.bookreader.screens.home.HomeScreenViewModel
import com.tispunshahryar960103.bookreader.utils.formatDate

@RequiresApi(Build.VERSION_CODES.N)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun UpdateScreen(
    navController: NavController,
    bookItemId: String,
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {
    
    Scaffold(topBar = { ReaderAppBar(
        navController = navController,
        title = "Update Book",
        icon = Icons.Default.ArrowBack,
        showProfile = false
    ){
        navController.popBackStack()
    }}) {

        /*
        produce new state for receiving data
         */

        val bookInfo = produceState<DataOrException<List<MBook>,Boolean,Exception>>(
            initialValue = DataOrException(data = emptyList(),true,Exception("")) ,
            producer = {
                value = viewModel.data.value
            }
        ).value


        Surface(modifier = Modifier
            .fillMaxSize()
            .padding(3.dp)) {

            Column(
                modifier = Modifier.padding(top = 3.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = CenterHorizontally
            ) {
                Log.d("INFO", "UpdateScreen: ${viewModel.data.value.data.toString()}")

                if (bookInfo.loading == true){
                    LinearProgressIndicator()
                    bookInfo.loading = false
                }else{
                    Surface(modifier = Modifier
                        .padding(2.dp)
                        .fillMaxWidth(),
                        shape = CircleShape,
                        elevation = 4.dp) {

                        ShowBookUpdate(bookInfo = viewModel.data.value, bookItemId = bookItemId)

                    }

                    ShowSimpleForm(book = viewModel.data.value.data?.first { mBook ->
                        mBook.googleBookId == bookItemId
                    }!!,navController = navController)


                }

                }
            
            }
        }
        
    }

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ShowSimpleForm(book: MBook, navController: NavController) {

    val context = LocalContext.current

    val notesText = remember {
        mutableStateOf("")
    }
    val isStartedReading = remember {
        mutableStateOf(false)
    }

    val isFinishedReading = remember {
        mutableStateOf(false)

    }
    val ratingVal = remember {
        mutableStateOf(0)
    }

    SimpleForm(defaultValue = if (book.notes.toString().isNotEmpty()) book.notes.toString()
    else "No thoughts available."){  note ->

        notesText.value = note

    }
    
    Row(modifier = Modifier.padding(4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start) {
        TextButton(
            onClick = { isStartedReading.value = true },
            enabled = book.startedReading == null
        ) {
           if (book.startedReading == null){
               if (!isStartedReading.value){
                   Text(text = "Start Reading!")
               }else{
                   Text(
                       text = "Started Reading!",
                       modifier = Modifier.alpha(0.6f),
                       color = Color.Red.copy(alpha = 0.5f)
                   )

               }
           }else{
               Text("Started on: ${formatDate(book.startedReading!!)}")

           }

        }
        Spacer(modifier = Modifier.height(4.dp))
        TextButton(onClick = { isFinishedReading.value = true },
            enabled = book.finishedReading == null) {
            if (book.finishedReading == null) {
                if (!isFinishedReading.value) {
                    Text(text = "Mark as Read")
                }else {
                    Text(text = "Finished Reading!")
                }
            }else {
                Text(text = "Finished on: ${formatDate(book.finishedReading!!)}")
            }

        }

    }
    Text(text = "Rating", modifier = Modifier.padding(bottom = 3.dp))
    book.rating?.toInt().let {
        RatingBar(rating = it!!){ rating->
            ratingVal.value = rating
            Log.d("TAG", "ShowSimpleForm: ${ratingVal.value}")
        }

    }

    Spacer(modifier = Modifier.padding(bottom = 15.dp))
    Row{
        val changeNotes = book.notes != notesText.value
        val changeRating = book.rating?.toInt() != ratingVal.value
        val isFinishedTimestamp = if (isFinishedReading.value) Timestamp.now() else book.finishedReading
        val isStartedTimestamp = if (isStartedReading.value) Timestamp.now() else book.startedReading

        val bookUpdate = changeNotes || changeRating || isFinishedReading.value || isStartedReading.value
        val bookToUpdate = hashMapOf(
            "finished_reading_at" to isFinishedTimestamp,
            "started_reading_at" to isStartedReading,
            "rating" to ratingVal.value,
            "notes" to notesText.value
        ).toMap()

        RoundedButton(label = "Update"){

            if (bookUpdate){
                FirebaseFirestore.getInstance()
                    .collection("books")
                    .document(book.id!!)
                    .update(bookToUpdate)
                    .addOnCompleteListener{
                        showToast(context,"Updated Book Successfully!")
                        navController.navigate(ReaderScreens.HomeScreen.name)
                      //  Log.d("Update", "ShowSimpleForm: ${task.result.toString()}")
                        

                    }
                    .addOnFailureListener{
                        Log.w("Error", "ShowSimpleForm: Error updating document", it)
                    }
            }



        }
        Spacer(modifier = Modifier.width(100.dp))

        val openDialog = remember{
            mutableStateOf(false)
        }
        if (openDialog.value){
            ShowAlertDialog(
                message = stringResource(id = R.string.sure) + "\n" + R.string.action,
                openDialog = openDialog
            ){
                FirebaseFirestore.getInstance()
                    .collection("books")
                    .document(book.id!!)
                    .delete()
                    .addOnCompleteListener{
                        if (it.isSuccessful){
                            openDialog.value = false
                            /*
                            Don't popBackStack() if we want the immediate recomposition
                            of the MainScreen UI, instead navigate to the mainScreen!
                           */

                           // navController.popBackStack() // this don't recompose home screen and we don't have last data update in home screen
                            navController.navigate(ReaderScreens.HomeScreen.name) // if we want to see changes in home screen
                        }
                    }
                    .addOnFailureListener{}

            }

        }
        RoundedButton(label = "Delete"){
            openDialog.value = true
        }
    }





}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SimpleForm( modifier: Modifier = Modifier,
                loading: Boolean = false,
                defaultValue: String = "Great Book!",
                onSearch: (String) -> Unit) {
    Column {
        val textFieldValue = rememberSaveable { mutableStateOf(defaultValue) }
        val keyboardController = LocalSoftwareKeyboardController.current
        val valid = remember(textFieldValue.value) { textFieldValue.value.trim().isNotEmpty() }

        InputField(
            modifier
                .fillMaxWidth()
                .height(140.dp)
                .padding(3.dp)
                .background(Color.White, CircleShape)
                .padding(horizontal = 20.dp, vertical = 12.dp),
            valueState = textFieldValue,
            labelId = "Enter Your thoughts",
            enabled = true,
            keyboardActions = KeyboardActions {
                if (!valid)return@KeyboardActions
                onSearch(textFieldValue.value.trim())
                keyboardController?.hide()
            })

    }

}

@Composable
fun ShowBookUpdate(
    bookInfo: DataOrException<List<MBook>, Boolean, Exception>,
    bookItemId: String,
) {
    Row() {
        Spacer(modifier = Modifier.width(43.dp))
        if (bookInfo.data != null) {
            Column(modifier = Modifier.padding(4.dp),
                verticalArrangement = Arrangement.Center
            ) {
             CardListItem(book = bookInfo.data!!.first { mBook ->
                 mBook.googleBookId == bookItemId
             }, onPressDetails = {})

            }
        }

    }

}

@Composable
fun CardListItem(book: MBook, onPressDetails: () -> Unit) {
    Card(modifier = Modifier
        .padding(
            start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp
        )
        .clip(RoundedCornerShape(20.dp))
        .clickable { },
        elevation = 8.dp) {
        Row(horizontalArrangement = Arrangement.Start) {

            Image(painter = rememberAsyncImagePainter(model = book.photoUrl.toString()),
                contentDescription = null ,
                modifier = Modifier
                    .height(100.dp)
                    .width(120.dp)
                    .padding(4.dp)
                    .clip(
                        RoundedCornerShape(
                            topStart = 120.dp, topEnd = 20.dp, bottomEnd = 0.dp, bottomStart = 0.dp
                        )
                    ))
            Column {
                Text(text = book.title.toString(),
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp)
                        .width(120.dp),
                    fontWeight = FontWeight.SemiBold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis)

                Text(text = book.authors.toString(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 8.dp,
                        end = 8.dp,
                        top = 2.dp,
                        bottom = 0.dp))

                Text(text = book.publishedDate.toString(),
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(start = 8.dp,
                        end = 8.dp,
                        top = 0.dp,
                        bottom = 8.dp))

            }

        }

    }

}
