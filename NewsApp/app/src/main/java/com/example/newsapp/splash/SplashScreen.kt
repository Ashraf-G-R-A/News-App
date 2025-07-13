package com.example.newsapp.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.newsapp.R
import com.example.newsapp.navigation.Routes
import com.example.newsapp.onboarding.OnboardingViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navHome: () -> Unit,
    navOnboarding: () -> Unit,
    viewModel: OnboardingViewModel = hiltViewModel()
) {
    val completed by viewModel.onboardingCompleted.collectAsState(initial = false)

    LaunchedEffect(completed) {
        delay(2000)
        if (completed) {
            navHome()
        } else {
            navOnboarding()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .fillMaxWidth()
        )
    }
}
