package com.base.baseproject.ui.home.event.step3.repository

import com.base.baseproject.ui.home.datastroe.preference.repository.PreferenceDataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TestStateFlowRepository @Inject constructor(
    private val preferenceDataStoreRepository: PreferenceDataStoreRepository
) {
    // StateFlow는 UI상태를 관리하기 때문에 ViewModel에서 하는게 적합하므로 flow 반환 후, viewModel에서 stateFlow로 처리
    fun testFlow(): Flow<List<String>> = preferenceDataStoreRepository.getStringListFlow()


    suspend fun insertStr(data: String) {
        preferenceDataStoreRepository.insertStrList(data)
    }

    suspend fun deleteStr(data: String) {
        preferenceDataStoreRepository.deleteStrList(data)
    }

    suspend fun updateStr(oldData: String, newData: String){
        preferenceDataStoreRepository.updateStrList(oldData, newData)
    }

}