package com.tispunshahryar960103.bookreader.screens.details

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.tispunshahryar960103.bookreader.components.ReaderAppBar
import com.tispunshahryar960103.bookreader.data.Resource
import com.tispunshahryar960103.bookreader.model.Item
import com.tispunshahryar960103.bookreader.navigation.ReaderScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(
    navController: NavController,
    bookId: String,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    
    Scaffold(topBar = {
        ReaderAppBar(
            title = "Book Details",
            navController = navController,
            icon = Icons.Default.ArrowBack,
            showProfile = false
        ){
            navController.navigate(ReaderScreens.SearchScreen.name)

        }
    }) {

        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxSize()) {
            Column(modifier = Modifier.padding(top = 12.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {

                /*
                1- "produceState" function is used for produce our necessary States.
                2- Type of this state(s) is passed in <StateTypeName> Like below example : produceState<StateTypeName>
                3- "produceState" has two parameter: first: initial value and second: producer is for how to create the states values
                 */
                val bookInfo = produceState<Resource<Item>>(initialValue = Resource.Loading()){
                    value = viewModel.getBookInfo(bookId = bookId)

                }.value
                if (bookInfo.data == null){
                    Row {

                        LinearProgressIndicator()
                        Text(text = "Loading...")
                    }

                }else{

                    ShowBookDetails(bookInfo=bookInfo,navController=navController)

                }
              //  Log.d("TAG", "DetailScreen: ${bookInfo.data.toString()}")

            }

        }
    }


}

@Composable
fun ShowBookDetails(bookInfo: Resource<Item>, navController: NavController) {

    val bookData= bookInfo.data?.volumeInfo
    val googleBookId = bookInfo.data?.id

    Card(
        modifier = Modifier.padding(34.dp),
        shape = CircleShape,
        elevation = 4.dp
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = bookData!!.imageLinks.thumbnail.toString()),
            contentDescription = "book Image",
            modifier = Modifier
                .width(90.dp)
                .height(90.dp)
                .padding(1.dp)
        )

    }

    Text(
        text = bookData?.title.toString(),
        style = MaterialTheme.typography.h6,
        overflow = TextOverflow.Ellipsis,
        maxLines = 19
    )
    Text(text = "Authors: ${bookData?.authors.toString()}")
    Text(text = "PageCount: ${bookData?.pageCount.toString()}")
    Text(text = "Categories: ${bookData?.categories.toString()}", style = MaterialTheme.typography.subtitle1)
    Text(text = "Published: ${bookData?.publishedDate.toString()}", style = MaterialTheme.typography.subtitle1)
    Spacer(modifier = Modifier.width(5.dp))



}




