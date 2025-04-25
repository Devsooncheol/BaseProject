package com.base.baseproject.ui.home.event.step2.repository

import com.base.baseproject.ui.home.datastroe.preference.repository.PreferenceDataStoreRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class TestFlowRepository @Inject constructor(
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository,
){
    private val refreshIntervalMs: Long = 5000

    val testFlow: Flow<Int> = flow{
        while(true) {
            val count = preferenceDataStoreRepository.getTestValue()
            emit(count)
            preferenceDataStoreRepository.incrementTestValue()
            delay(refreshIntervalMs)
        }
    }
}