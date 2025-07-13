package com.example.newsapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.newsapp.home.HomeViewModel
import com.example.newsapp.home.view.HomeScreen
import com.example.newsapp.onboarding.view.OnboardingScreen
import com.example.newsapp.search.view.SearchScreen
import com.example.newsapp.splash.SplashScreen

@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Routes.Splash.route) {

        composable(route = Routes.Splash.route) {
            SplashScreen(navHome = {
                navController.navigate(Routes.Home.route) {
                    popUpTo(Routes.Splash.route) { inclusive = true }
                }
            }, navOnboarding = {
                navController.navigate(Routes.Onboarding.route) {
                    popUpTo(Routes.Splash.route) { inclusive = true }
                }
            })
        }



        composable(route = Routes.Onboarding.route) {
            OnboardingScreen(navController = navController)
        }

        composable(route = Routes.Home.route) {

            HomeScreen(
                navSearch = {
                    navController.navigate(Routes.Search.route)
                },
                navDetails = {
                    navController.navigate(Routes.DetailsScreen.route)
                })
        }
        composable(route = Routes.Search.route) {
            SearchScreen(navDetails = {
                navController.navigate(Routes.DetailsScreen.route)
            })
        }

        composable(route = Routes.DetailsScreen.route) {
//            DetailsScreen(navController = navController)
        }
    }

}




