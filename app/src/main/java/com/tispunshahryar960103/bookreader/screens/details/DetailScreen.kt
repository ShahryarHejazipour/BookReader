package com.tispunshahryar960103.bookreader.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tispunshahryar960103.bookreader.components.ReaderAppBar
import com.tispunshahryar960103.bookreader.navigation.ReaderScreens

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailScreen(navController: NavController,bookId:String) {
    
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

                Text(text = "BookId : $bookId")

            }

        }
    }


}