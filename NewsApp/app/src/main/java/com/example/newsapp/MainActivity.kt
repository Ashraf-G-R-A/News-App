package com.example.newsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.navigation.compose.rememberNavController
import com.example.newsapp.navigation.AppNavGraph
import com.example.newsapp.onboarding.view.OnboardingScreen
import com.example.newsapp.ui.theme.NewsAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        enableEdgeToEdge()
        setContent {
            NewsAppTheme {
                val isSystemInDarkTheme = isSystemInDarkTheme()
                val systemUiController = rememberSystemUiController()
                val navController = rememberNavController()

                systemUiController.setSystemBarsColor(
                    color = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                    darkIcons = !isSystemInDarkTheme
                )
                AppNavGraph(navController)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun OnboardingScreenPreview() {
    val navController = rememberNavController()
    OnboardingScreen(navController)
}
