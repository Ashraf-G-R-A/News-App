package com.example.data.repo.onboarding

import com.example.data.local.datastore.DataStoreManager
import com.example.domain.onboarding.repo.OnboardingRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : OnboardingRepository {

    override suspend fun saveOnboardingState(completed: Boolean) {
        dataStoreManager.saveOnboardingCompleted(completed)
    }

    override fun readOnboardingState(): Flow<Boolean> {
        return dataStoreManager.onboardingCompleted
    }
}
