package com.tispunshahryar960103.bookreader.screens.search

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tispunshahryar960103.bookreader.components.InputField
import com.tispunshahryar960103.bookreader.components.ReaderAppBar
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
                SearchForm(modifier = Modifier.fillMaxWidth().padding(16.dp)){

                }

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