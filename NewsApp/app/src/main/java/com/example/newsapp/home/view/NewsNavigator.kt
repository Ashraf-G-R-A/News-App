package com.example.newsapp.home.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.R
import com.example.newsapp.navigation.AppNavGraph
import com.example.newsapp.navigation.Routes
import okhttp3.Route

@Composable
fun NewsNavigation() {
    val bottomNavigationItems = remember {
        listOf(
            BottomNavigationItem(icon = R.drawable.ic_home, text = "Home"),
            BottomNavigationItem(icon = R.drawable.ic_search, text = "Search"),
            BottomNavigationItem(icon = R.drawable.ic_bookmark, text = "Bookmark"),
        )
    }

    val navController = rememberNavController()
    val backstackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable { mutableIntStateOf(0) }

    selectedItem = when (backstackState?.destination?.route) {
        Routes.Home.route -> 0
        Routes.Search.route -> 1
        Routes.Bookmark.route -> 2
        else -> 0
    }

    Scaffold(
        bottomBar = {
            NewsBottomNavigation(
                items = bottomNavigationItems,
                selectedItem = selectedItem,
            ) { index ->
                when (index) {
                    0 -> navigationToTop(navController, Routes.Home.route)
                    1 -> navigationToTop(navController, Routes.Search.route)
                    2 -> navigationToTop(navController, Routes.Bookmark.route)
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        AppNavGraph(navController = navController, paddingValues = paddingValues)
    }
}

fun navigationToTop(navController: NavController, route: String) {
    navController.navigate(route) {
        popUpTo(navController.graph.startDestinationId) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }

}
