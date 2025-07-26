package com.example.newsapp.navigation

sealed class Routes(val route: String) {
    object Onboarding : Routes("onboarding")
    object Home : Routes("home")
    object Splash : Routes("splash")
    object Search : Routes("search")
    object DetailsScreen : Routes("detailsScreen")
    object Bookmark : Routes("bookmark")

}
