package com.example.buildconnectapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.buildconnectapp.screens.AdvertisementsScreen
import com.example.buildconnectapp.screens.CalendarScreen
import com.example.buildconnectapp.screens.ForumScreen
import com.example.buildconnectapp.screens.LoginScreen
import com.example.buildconnectapp.screens.MenuScreen
import com.example.buildconnectapp.screens.SignUpScreen
import com.example.buildconnectapp.screens.UserFormScreen
import com.example.buildconnectapp.screens.UserListScreen

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
        composable(route = AppScreens.UserListScreen.route){
            UserListScreen(navController)
        }
        composable(route = AppScreens.UserFormScreen.route, arguments = listOf(navArgument("userId") { defaultValue = "" })) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId") ?: ""
            UserFormScreen(navController, userId)
        }

    }
}