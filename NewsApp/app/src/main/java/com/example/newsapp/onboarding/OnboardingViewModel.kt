package com.example.newsapp.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.onboarding.usecase.ReadOnboardingStateUseCase
import com.example.domain.onboarding.usecase.SaveOnboardingStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class OnboardingViewModel @Inject constructor(
    private val saveOnboardingStateUseCase: SaveOnboardingStateUseCase,
    private val readOnboardingStateUseCase: ReadOnboardingStateUseCase
) : ViewModel() {
    val onboardingCompleted = readOnboardingStateUseCase()
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000),
            initialValue = false
        )

    suspend fun saveOnboardingState(completed: Boolean) {
        saveOnboardingStateUseCase(completed)
    }

}