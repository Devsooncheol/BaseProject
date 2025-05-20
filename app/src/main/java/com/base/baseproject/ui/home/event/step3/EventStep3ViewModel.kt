package com.base.baseproject.ui.home.event.step3

import android.content.Intent
import androidx.lifecycle.viewModelScope
import com.base.baseproject.ui.home.event.step3.repository.TestStateFlowRepository
import com.base.module.base.BaseViewModel
import com.base.module.base.constants.BaseConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject


/**
 * Kotlin StateFlow 정리
 *
 *  1. Hot Stream
 *     - 항상 값을 가지고 있으며 구독 여부와 상관없이 최신 값을 유지함
 *
 *  2. 초기값 필요
 *     - 반드시 initialValue가 필요하며, 첫 collect 시 해당 값이 발행됨
 *
 *  3. Cold Flow -> Hot StateFlow 변환 시 stateIn 사용
 *     - Flow를 ViewModel 등에서 상태로 관리할 때 사용
 *     - 구독자의 생명주기에 따라 자동 시작/중단이 가능해짐
 *
 *  4. stateIn은 SharingStarted 정책 선택이 중요함
 *      - Eagerly
 *          : ViewModel이 생성되자마자 시작, 구독자가 없어도 계속 실행
 *          : 항상 데이터가 필요한 경우 적합 (ex. 센서, 실시간 서버)
 *
 *      - Lazily
 *          : 첫 구독자가 생길 때 실행, 이후에는 멈추지 않음
 *          : 중단되지 않아서 메모리 지속 사용 가능성 있음
 *
 *      - WhileSubscribed(timeoutMillis)
 *          : 구독자가 있을 때만 실행되고, 없으면 지정된 시간 후 중단
 *          : UI 상태 관리에 가장 적합 (ex. 화면이 사라지면 자동 중단)
 *
 */
@HiltViewModel
class EventStep3ViewModel @Inject constructor(
    private val testStateFlowRepository: TestStateFlowRepository
) : BaseViewModel() {

    //flow
    val testFlow = testStateFlowRepository.testFlow()

    /**
     * StateFlow (Case1)
     *  - Flow값을  collectLatest로 수집하여 그 값을 StateFlow에 옮겼다
     *  - emit()을 사용하여 _stateFlowData에 값을 전달할 수도 있다.
     *  - StateFlow는 초기 값이 필요하기 때문에 emptyList로 초기화
     * */
    private var _testStateFlow = MutableStateFlow<List<String>>(emptyList())
    val testStateFlow: StateFlow<List<String>> = _testStateFlow

    fun testSelectStateFlow() {
        viewModelScope.launch {
            testStateFlowRepository.testFlow().collectLatest {
                _testStateFlow.value = it
                // _stateFlowData.emit(it)
            }
        }
    }

    /**
     * StateFlow (Case2)
     *  - StateIn() 함수 사용
     *   - scope: 생명주기를 지정하는 코루틴 스코프
     *   - started:  startedSharingStarted 클래스의 인스턴스로 StateFlow를 언제부터 공유할 것인지를 지정
     *    : Eagerly 즉시 시작하여 멈추지 않느 ㄴ속성
     *    : Lazily는 첫 번째 구독자가 나타날 때 시작하여 멈추지 않는 속성
     *    : WhileSubscribed(milliSecond)는 첫 번째 구독자가 나타날 때 시작하며 구독 없으면 X초 후 정지 (UI 상태 관리에 가장 효율적)
     *   - initialValue: StateFlow 초기값
     * */

    // Case 2
    fun testStateFlow2() = testStateFlowRepository.testFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Lazily,
        initialValue = emptyList()
    )

    fun insertStr(data: String) {
        viewModelScope.launch {
            testStateFlowRepository.insertStr(data)
        }
    }

    fun deleteStr(data: String) {
        viewModelScope.launch {
            testStateFlowRepository.deleteStr(data)
        }
    }

    fun updateStr(oldData: String, newData: String) {
        viewModelScope.launch {
            testStateFlowRepository.updateStr(oldData, newData)
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