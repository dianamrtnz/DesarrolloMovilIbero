package com.example.buildconnectapp.navigation

sealed class AppScreens(val route: String) {
    object LoginScreen: AppScreens("login_screen")
    object SignUpScreen: AppScreens("signup_screen")
    object MenuScreen: AppScreens("menu_screen")
    object AdvertisementsScreen: AppScreens("advertisements_screen")
    object CalendarScreen: AppScreens("calendar_screen")
    object ForumScreen: AppScreens("forum_screen")
}