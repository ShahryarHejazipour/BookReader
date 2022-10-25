package com.tispunshahryar960103.bookreader.screens.search

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.tispunshahryar960103.bookreader.components.InputField
import com.tispunshahryar960103.bookreader.components.ReaderAppBar
import com.tispunshahryar960103.bookreader.model.MBook
import com.tispunshahryar960103.bookreader.navigation.ReaderScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Preview
@Composable
fun SearchScreen(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(topBar = { ReaderAppBar(
        navController = navController,
        title = "Search Books",
        icon = Icons.Default.ArrowBack,
        showProfile = false
    ){
        navController.popBackStack()
      //  navController.navigate(ReaderScreens.HomeScreen.name)
    }}) {
        Surface() {
            Column {
                SearchForm(modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)){
                    Log.d("TAG", "SearchScreen: $it")

                }

                Spacer(modifier = Modifier.height(13.dp))

                BookList(navController = navController)

            }

        }
        
    }

}

@Composable
fun BookList(navController: NavController) {


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

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(4.dp)
    ){
        items(items = listOfBooks){ book ->
            BookRaw(book,navController)

        }

    }

}

@Composable
fun BookRaw(book: MBook, navController: NavController) {
    Card(
        modifier = Modifier
            .clickable { }
            .padding(3.dp)
            .fillMaxWidth()
            .height(100.dp),
        shape = RectangleShape,
        elevation = 7.dp
    ) {
        Row(modifier = Modifier.padding(5.dp), verticalAlignment = Alignment.Top) {
            val imageUrl = "https://images.unsplash.com/photo-1541963463532-d68292c34b19?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=80&q=80"
            Image(
                painter = rememberAsyncImagePainter(model = imageUrl),
                contentDescription = "book image",
                modifier = Modifier.padding(end=4.dp).width(80.dp).fillMaxHeight()
            )
            Column {
                Text(text = book.title.toString(), overflow = TextOverflow.Ellipsis)
                Text(
                    text = "Authors : ${book.authors.toString()}",
                    overflow = TextOverflow.Clip,
                    style = MaterialTheme.typography.caption
                )

                //todo: add more fields later!


            }

        }

    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    loading : Boolean = false,
    hint : String = "Search",
    onSearch : (String) -> Unit = {}
) {
    val searchQueryState = rememberSaveable{ mutableStateOf("")}
    val keyBoardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value){
        searchQueryState.value.trim().isNotEmpty()
    }
    
    InputField(valueState = searchQueryState,
        labelId = "Search",
        enabled = true,
        keyboardActions = KeyboardActions {
            if (!valid){
                return@KeyboardActions
            }
            onSearch(searchQueryState.value.trim())
            searchQueryState.value = ""
            keyBoardController?.hide()
        })

}