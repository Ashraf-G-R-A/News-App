package com.example.newsapp.onboarding.minview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.onboarding.model.OnboardingPage
import com.example.newsapp.ui.theme.PoppinsBold
import com.example.newsapp.ui.theme.PoppinsSemiBold

@Composable
fun OnboardingPageScreen(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.material3.MaterialTheme.colorScheme.onPrimary),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            painter = painterResource(id = page.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(450.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = page.title,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontFamily = PoppinsBold,
            fontSize = 28.sp,
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = page.description,
            color = androidx.compose.material3.MaterialTheme.colorScheme.secondary,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontFamily = PoppinsSemiBold,
            fontSize = 16.sp,
        )
    }
}