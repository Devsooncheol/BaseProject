package com.base.baseproject.ui.home.datastroe.preference.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * **DataStore**는 Android에서 데이터를 비동기적이고 안전하게 저장하는 새로운 API로, `SharedPreferences`를 대체
 * 주요 특징:
 *
 * 1. 비동기 데이터 처리:
 *    - UI 스레드를 차단하지 않으며, `Flow`와 `suspend`를 사용해 성능이 뛰어남
 *
 * 2. 안전한 데이터 저장:
 *    - 트랜잭션 방식으로 데이터를 안전하게 처리하고, `Coroutine`을 활용하여 비동기 작업을 처리
 *
 * 3. 키-값 저장 방식:
 *    - `Preferences DataStore`는 `SharedPreferences`처럼 데이터를 키-값 쌍으로 저장합니다. 기본 타입을 저장하고,
 *    - `Proto DataStore`는 복잡한 구조를 지원
 *
 * 4. Flow 기반 비동기 데이터 읽기:
 *    - `Flow`를 사용하여 데이터 변경 사항을 실시간으로 관찰하고 반영
 *
 * 5. 자동 저장:
 *    - `DataStore`는 데이터 변경 시 자동으로 저장되며, 별도의 `commit()` 호출이 필요 없음
 *
 * 6. 타입 안전성:
 *    - `Proto DataStore`는 `Protocol Buffers`(ProtoBuf)를 사용하여 강력한 타입 안전성을 제공
 *
 * 주요 메서드:
 * - `dataStore.data`: 데이터를 비동기적으로 읽는 `Flow`입니다. 값이 변경되면 최신 값을 반환
 * - `dataStore.edit`: 데이터를 수정하고 즉시 저장합니다.
 *
 * 사용 방법:
 * - `testValueFlow`: `DataStore`에서 `testValue` 값을 비동기적으로 읽는 `Flow<Int>`입니다.
 * - `incrementTestValue()`: `testValue` 값을 읽고 1을 증가시킨 후 저장합니다.
 *
 * // 확인사항
 *  - String만 암호화 처리 안되어있음
 *  - 다른 데이터 타입은 암호화 되어있음
 *  - file Explorer에서 확인 가능
 *  - 마이그레이션 관련 알아볼것
 *
 */
class PreferenceDataStoreRepository @Inject constructor(
    @ApplicationContext context: Context,
    private val dataStore: DataStore<Preferences>
) {
    companion object{
        private val TEST_VALUE = intPreferencesKey("testValue")
        private val TEST_STRING_LIST_VALUE_KEY = stringPreferencesKey("string_list_key")
    }

    /**
     * Int Test
     * */
    suspend fun getTestValue(): Int {
        return dataStore.data.first()[TEST_VALUE] ?: 0
    }

    val testValueFlow: Flow<Int> = dataStore.data
        .map { preferences -> preferences[TEST_VALUE] ?: 0 }

    suspend fun incrementTestValue() {
        dataStore.edit { preferences ->
            val currentValue = preferences[TEST_VALUE] ?: 0
            preferences[TEST_VALUE] = currentValue.plus(1)
        }
    }

    /**
     * List<String> Test
     * */
    suspend fun insertStrList(item: String) {
        dataStore.edit { preferences ->
            val currentList = preferences[TEST_STRING_LIST_VALUE_KEY]?.split(",")
                ?.map { it.trim() }
                ?.filter { it.isNotEmpty() }
                ?.toMutableList() ?: mutableListOf()


            if (item !in currentList) {
                currentList.add(item)
            }

            preferences[TEST_STRING_LIST_VALUE_KEY] = if (currentList.isEmpty()) ""
            else currentList.joinToString(",")
        }
    }

    suspend fun deleteStrList(item: String) {
        dataStore.edit { preferences ->
            val currentList = preferences[TEST_STRING_LIST_VALUE_KEY]?.split(",")
                ?.map { it.trim() }
                ?.filter { it.isNotEmpty() }
                ?.toMutableList() ?: mutableListOf()

            currentList.remove(item)
            preferences[TEST_STRING_LIST_VALUE_KEY] = if (currentList.isEmpty()) ""
            else currentList.joinToString(",")
        }
    }

    suspend fun updateStrList(oldItem: String, newItem: String) {
        dataStore.edit { preferences ->
            val currentList = preferences[TEST_STRING_LIST_VALUE_KEY]?.split(",")
                ?.map { it.trim() }
                ?.filter { it.isNotEmpty() }
                ?.toMutableList() ?: mutableListOf()

            val index = currentList.indexOf(oldItem)
            if (index != -1) {
                currentList[index] = newItem
                preferences[TEST_STRING_LIST_VALUE_KEY] = if (currentList.isEmpty()) ""
                else currentList.joinToString(",")
            }
        }
    }

    fun getStringListFlow(): Flow<List<String>> {
        return dataStore.data
            .map { preferences ->
                val joined = preferences[TEST_STRING_LIST_VALUE_KEY] ?: ""
                if (joined.isBlank()) emptyList() else joined.split(",")
            }
    }

}