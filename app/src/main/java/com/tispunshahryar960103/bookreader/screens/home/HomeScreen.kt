package com.tispunshahryar960103.bookreader.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.tispunshahryar960103.bookreader.components.*
import com.tispunshahryar960103.bookreader.model.MBook
import com.tispunshahryar960103.bookreader.navigation.ReaderScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController) {

    Scaffold(topBar = {
                      ReaderAppBar(title = "Book Reader", navController = navController)
    }, floatingActionButton = {FABContent{}}) {
        //content
        Surface(modifier = Modifier.fillMaxSize()) {
            // home content
            HomeContent(navController = navController)
            
        }
    }
}

//@Preview
@Composable  
fun HomeContent(navController: NavController ) {

      val listOfBooks = listOf(
       MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
      MBook(id = "dadfa", title = " Again", authors = "All of us", notes = null),
       MBook(id = "dadfa", title = "Hello ", authors = "The world us", notes = null),
       MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
       MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
          MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
          MBook(id = "dadfa", title = " Again", authors = "All of us", notes = null),
          MBook(id = "dadfa", title = "Hello ", authors = "The world us", notes = null),
          MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null),
          MBook(id = "dadfa", title = "Hello Again", authors = "All of us", notes = null)
                           )
    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName = if (!email.isNullOrEmpty())
                         email.split("@")[0]
                          else "Not available"

    
    Column(modifier = Modifier.padding(2.dp), verticalArrangement = Arrangement.Top) {
        Row(modifier = Modifier.align(alignment = Alignment.Start)) {
            TitleSection(label = "Your reading\n " + "activity right now ...")
            Spacer(modifier = Modifier.fillMaxWidth(fraction = 0.7f))
            Column {
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.StatesScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colors.secondaryVariant
                )
                Text(
                    text = currentUserName,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Divider()
            }

        }
        ReadingRightNowArea(books = listOf(), navController = navController)
        TitleSection(label = "Reading List")
        BookListArea(listOfBooks = listOfBooks,navController = navController)

        
    }
}

@Composable
fun BookListArea(listOfBooks: List<MBook>, navController: NavController) {

    HorizontalScrollableArea(listOfBooks){
        Log.d("TAG", "BookListArea: $it")
        //todo: on card clicked navigate to the Details
    }

}

@Composable
fun HorizontalScrollableArea(listOfBooks: List<MBook>,onCardPressed : (String) -> Unit) {

    val scrollState = rememberScrollState()
    Row(modifier = Modifier
        .fillMaxWidth()
        .heightIn(280.dp)
        .horizontalScroll(scrollState)) {
        for (book in listOfBooks){
            ListCard(book){

                onCardPressed(it)

            }
        }
    }


}


@Composable
fun ReadingRightNowArea(books:List<MBook>, navController: NavController) {
    ListCard()

}

