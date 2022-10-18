package com.tispunshahryar960103.bookreader.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tispunshahryar960103.bookreader.screens.details.DetailScreen
import com.tispunshahryar960103.bookreader.screens.home.HomeScreen
import com.tispunshahryar960103.bookreader.screens.login.BookReaderLoginScreen
import com.tispunshahryar960103.bookreader.screens.search.SearchScreen
import com.tispunshahryar960103.bookreader.screens.splash.SplashScreen
import com.tispunshahryar960103.bookreader.screens.states.StatesScreen
import com.tispunshahryar960103.bookreader.screens.update.UpdateScreen

@Composable
fun ReaderNavigation() {

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ReaderScreens.SplashScreen.name){

        composable(ReaderScreens.SplashScreen.name){
            SplashScreen(navController = navController)
        }
        composable(ReaderScreens.HomeScreen.name){
            HomeScreen(navController = navController)
        }
        composable(ReaderScreens.DetailsScreen.name){
            DetailScreen(navController = navController)
        }
        composable(ReaderScreens.LoginScreen.name){
            BookReaderLoginScreen(navController = navController)
        }
        composable(ReaderScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }
        composable(ReaderScreens.StatesScreen.name){
            StatesScreen(navController = navController)
        }
        composable(ReaderScreens.UpdateScreen.name){
            UpdateScreen(navController = navController)
        }
    }

    


    


}