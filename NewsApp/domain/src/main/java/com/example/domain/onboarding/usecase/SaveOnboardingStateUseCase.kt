package com.example.domain.onboarding.usecase

import com.example.domain.onboarding.repo.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SaveOnboardingStateUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {
    suspend operator fun invoke(completed: Boolean) {
        repository.saveOnboardingState(completed)
    }
}

