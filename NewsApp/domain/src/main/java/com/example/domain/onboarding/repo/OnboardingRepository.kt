package com.example.domain.onboarding.repo

import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {
    suspend fun saveOnboardingState(completed: Boolean)
    fun readOnboardingState(): Flow<Boolean>
}
