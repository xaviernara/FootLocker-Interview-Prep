package com.example.interviewprep.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

class NavGraph {

    @Composable
    fun RootNavigation(navHostController: NavHostController, route: String){
        NavHost(navController = navHostController, startDestination = "home"){
            composable(route = route){
//                HomeScreen()
            }
            composable(route = route){
//                ProfileScreen()
            }

        }
    }
}