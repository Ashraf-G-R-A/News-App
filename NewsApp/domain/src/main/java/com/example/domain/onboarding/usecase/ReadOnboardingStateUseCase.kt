package com.example.domain.onboarding.usecase

import com.example.domain.onboarding.repo.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ReadOnboardingStateUseCase @Inject constructor(
    private val repository: OnboardingRepository
) {
    operator fun invoke(): Flow<Boolean> {
        return repository.readOnboardingState()
    }
}
