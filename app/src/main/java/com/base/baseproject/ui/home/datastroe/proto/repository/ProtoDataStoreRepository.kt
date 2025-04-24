package com.base.baseproject.ui.home.datastroe.proto.repository

import androidx.datastore.core.DataStore
import com.base.baseproject.SettingsTest
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProtoDataStoreRepository @Inject constructor(
    private val dataStore: DataStore<SettingsTest>
) {

    val settingsFlow: Flow<SettingsTest> = dataStore.data

    suspend fun updateIntValue() {
        dataStore.updateData { currentSettings ->
            val newValue = currentSettings.intValue + 1
            currentSettings.toBuilder()
                .setIntValue(newValue)
                .build()
        }
    }

    suspend fun updateDoubleValue() {
        dataStore.updateData { currentSettings ->
            val newValue = currentSettings.doubleValue + 1.0
            currentSettings.toBuilder()
                .setDoubleValue(newValue)
                .build()
        }
    }

    suspend fun updateFloatValue() {
        dataStore.updateData { currentSettings ->
            val newValue = currentSettings.floatValue+ 1f
            currentSettings.toBuilder()
                .setFloatValue(newValue)
                .build()
        }
    }

    suspend fun updateLongValue() {
        dataStore.updateData { currentSettings ->
            val newValue = currentSettings.longValue + 1L
            currentSettings.toBuilder()
                .setLongValue(newValue)
                .build()
        }
    }

    suspend fun updateBooleanValue() {
        dataStore.updateData { currentSettings ->
            val newValue = !currentSettings.isFlag
            currentSettings.toBuilder()
                .setIsFlag(newValue)
                .build()
        }
    }

    suspend fun updateStringValue() {
        dataStore.updateData { currentSettings ->
            val currentStrValue = currentSettings.strMessage.ifEmpty { "a" }.first()
            val nextChar = when (currentStrValue) {
                in 'a'..'z' -> {
                    if (currentStrValue == 'z') 'A' else currentStrValue + 1
                }
                in 'A'..'Z' -> {
                    if (currentStrValue == 'Z') 'a' else currentStrValue + 1
                }
                else -> 'a'
            }
            currentSettings.toBuilder()
                .setStrMessage(nextChar.toString())
                .build()
        }
    }
}