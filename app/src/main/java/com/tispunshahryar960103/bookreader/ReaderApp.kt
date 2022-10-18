package com.tispunshahryar960103.bookreader

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tispunshahryar960103.bookreader.navigation.ReaderNavigation

@Composable
fun ReaderApp() {
    // A surface container using the 'background' color from the theme
    Surface(modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background) {
       Column(
           verticalArrangement = Arrangement.Center,
           horizontalAlignment = Alignment.CenterHorizontally
       ) {
           ReaderNavigation()
       }
    }
}