package com.tispunshahryar960103.bookreader.screens.update

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.tispunshahryar960103.bookreader.components.ReaderAppBar
import com.tispunshahryar960103.bookreader.data.DataOrException
import com.tispunshahryar960103.bookreader.model.MBook
import com.tispunshahryar960103.bookreader.screens.home.HomeScreenViewModel

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

                    Text(text = viewModel.data.value.data!![0].title.toString())

                }

                }
            
            }
        }
        
    }
