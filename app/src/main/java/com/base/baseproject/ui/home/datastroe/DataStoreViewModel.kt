package com.base.baseproject.ui.home.datastroe

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.base.baseproject.SettingsTest
import com.base.baseproject.ui.home.datastroe.proto.repository.ProtoDataStoreRepository
import com.base.module.base.BaseViewModel
import com.base.module.base.constants.BaseConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStorePreference: DataStorePreference,
    private val protoDataStoreRepository: ProtoDataStoreRepository,
) : BaseViewModel() {
    val testValueFlow: Flow<Int> = dataStorePreference.testValueFlow

    val settingsFlow: Flow<SettingsTest> = protoDataStoreRepository.settingsFlow

    fun incrementTestValue() {
        viewModelScope.launch {
            dataStorePreference.incrementTestValue()
        }
     }

    fun updateIntValue() {
        viewModelScope.launch {
            protoDataStoreRepository.updateIntValue()
        }
    }

    fun updateDoubleValue() {
        viewModelScope.launch {
            protoDataStoreRepository.updateDoubleValue()
        }
    }

    fun updateFloatValue() {
        viewModelScope.launch {
            protoDataStoreRepository.updateFloatValue()
        }
    }

    fun updateLongValue() {
        viewModelScope.launch {
            protoDataStoreRepository.updateLongValue()
        }
    }

    fun updateBooleanValue() {
        viewModelScope.launch {
            protoDataStoreRepository.updateBooleanValue()
        }
    }

    fun updateStringValue() {
        viewModelScope.launch {
            protoDataStoreRepository.updateStringValue()
        }
    }

    override fun getIntentExtraNext(intent: Intent): Intent {
        Timber.d("getIntentExtraNext, tag : $TAG")
        intent.putExtra(BaseConstants.INTENT_EXTRA_NEXT,TAG)
        return intent
    }

    override fun getIntentExtraFirst(intent: Intent): Intent {
        Timber.d("getIntentExtraFirst, tag : $TAG")
        intent.putExtra(BaseConstants.INTENT_EXTRA_FIRST,TAG)
        return intent
    }
}