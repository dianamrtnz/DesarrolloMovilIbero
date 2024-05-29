package com.example.buildconnectapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.buildconnectapp.screens.AdvertisementsScreen
import com.example.buildconnectapp.screens.CalendarScreen
import com.example.buildconnectapp.screens.ForumScreen
import com.example.buildconnectapp.screens.LoginScreen
import com.example.buildconnectapp.screens.MenuScreen
import com.example.buildconnectapp.screens.SignUpScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.LoginScreen.route) {
        composable(route = AppScreens.LoginScreen.route){
            LoginScreen(navController)
        }
        composable(route = AppScreens.SignUpScreen.route){
            SignUpScreen(navController)
        }
        composable(route = AppScreens.MenuScreen.route){
            MenuScreen(navController)
        }
        composable(route = AppScreens.AdvertisementsScreen.route){
            AdvertisementsScreen(navController)
        }
        composable(route = AppScreens.CalendarScreen.route){
            CalendarScreen(navController)
        }
        composable(route = AppScreens.ForumScreen.route){
            ForumScreen(navController)
        }
    }
}