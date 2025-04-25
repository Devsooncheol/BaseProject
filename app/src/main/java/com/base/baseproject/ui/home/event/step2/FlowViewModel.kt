package com.base.baseproject.ui.home.event.step2

import android.content.Intent
import com.base.baseproject.ui.home.event.step2.repository.TestFlowRepository
import com.base.module.base.BaseViewModel
import com.base.module.base.constants.BaseConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import timber.log.Timber
import javax.inject.Inject

/**
 * Kotlin Flow 정리
 *
 * 1. Cold Stream
 *  - Flow는 collect되기 전까지는 아무 일도 일어나지 않음 (lazy)
 *  - 데이터 생산이 구독 이후 시작됨 → 항상 새로운 데이터를 발행할 수 있음
 *  - 예: flow {...}, flowOf(...), .asFlow() 등
 *  - Flow는 "Cold Stream"이기 때문에 구독이 시작될 때 비로소 데이터를 생산하며,이로 인해 각 `collect`는 독립적으로 동작
 *  - 동기적으로 값을 얻는 value 형태는 사용 불가
 *
 * 2. UpStream
 *  - 데이터를 발행(emit)하는 쪽
 *  - 연산자의 입력이 되는 방향
 *  - 예: flow { emit(1) }, flowOf(1,2,3)
 *
 * 3. DownStream
 *  - 데이터를 소비하는 쪽
 *  - collect 등 최종 소비자를 통해 데이터를 소비하는 부분
 *  - map, filter 등의 연산자가 연결되며, 마지막에 collect로 완료
 *
 * 4. 중간연산자
 *  - 데이터를 변형하지만 실제로 실행되지 않음 (lazy)
 *  - 대표적으로 map, filter, take, debounce 등
 *  - flow는 중간 연산자를 통해 체이닝 형태로 가공 가능
 *
 * 5. 최종연산자
 *  - collect, toList, first, single 등
 *  - 이 연산자가 호출되어야 Flow가 실행됨
 *
 * 6. 예외처리
 *  - catch는 Flow 내에서 발생한 예외를 처리
 *  - 앞선 연산자에서 발생한 예외만 catch 가능
 *  - collect 안에서 발생한 예외는 catch로 잡히지 않음 → try-catch로 따로 감싸야 함
 *
 * 7. 기타 주요 개념
 *  - flowOn: Flow의 특정 구간의 실행 컨텍스트(Context)를 변경
 *  - buffer: 비동기 처리 시 성능 향상을 위한 버퍼링
 *  - conflate: 가장 최신 값만 유지 (중간 값 drop)
 *  - collectLatest: 가장 최신 emit만 처리, 이전 값 처리 중이면 취소됨
 *
 * */

@HiltViewModel
class FlowViewModel @Inject constructor(
    private val testFlowRepository: TestFlowRepository
) : BaseViewModel() {

    val testFlow: Flow<Int> = testFlowRepository.testFlow
        .map {
            it * 2
        }.filter {
            it % 2 == 0
        }.catch { e ->
            e.printStackTrace()
            if( e is IllegalStateException) throw e
            else emit(-1)
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