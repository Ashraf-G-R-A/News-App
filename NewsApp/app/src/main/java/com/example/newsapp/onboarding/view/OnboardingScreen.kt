package com.example.newsapp.onboarding.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.domain.onboarding.model.OnboardingPage
import com.example.newsapp.R
import com.example.newsapp.navigation.Routes
import com.example.newsapp.onboarding.OnboardingViewModel
import com.example.newsapp.onboarding.minview.DotsIndicator
import com.example.newsapp.onboarding.minview.OnboardingPageScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnboardingScreen(
    navController: NavHostController,
    viewModel: OnboardingViewModel = hiltViewModel()
) {

    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()


    val pages = listOf(
        OnboardingPage(
            R.drawable.onboarding1,
            "Lorem Ipsum is simply dummy",
            "Lorem Ipsum is simply dummy text of\nthe printing and typesetting industry."
        ),
        OnboardingPage(
            R.drawable.onboarding2,
            "Lorem Ipsum is simply dummy",
            "Lorem Ipsum is simply dummy text of\nthe printing and typesetting industry."
        ),
        OnboardingPage(
            R.drawable.onboarding3,
            "Lorem Ipsum is simply dummy",
            "Lorem Ipsum is simply dummy text of\nthe printing and typesetting industry."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.onPrimary),
        verticalArrangement = Arrangement.Top
    ) {
        HorizontalPager(
            count = pages.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            OnboardingPageScreen(pages[page])
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            DotsIndicator(
                totalDots = pages.size,
                selectedIndex = pagerState.currentPage
            )

            Row {
                if (pagerState.currentPage > 0) {
                    TextButton(onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage - 1)
                        }
                    }) {
                        Text(text = "Back", color = MaterialTheme.colorScheme.onPrimary)
                    }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        scope.launch {
                            if (pagerState.currentPage + 1 < pages.size) {
                                pagerState.animateScrollToPage(pagerState.currentPage + 1)
                            } else {
                                viewModel.saveOnboardingState(true)
                                navController.navigate(Routes.Home.route) {
                                    popUpTo(Routes.Onboarding.route) { inclusive = true }
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Text(
                        text = if (pagerState.currentPage + 1 < pages.size) "Next" else "Get Started",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}
