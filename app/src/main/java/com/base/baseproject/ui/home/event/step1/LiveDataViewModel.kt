package com.base.baseproject.ui.home.event.step1

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.base.module.base.BaseViewModel
import com.base.module.base.constants.BaseConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject


/**
 * LiveData 장점
 *
 * Ensures your UI matches your data state(UI와 Data의 상태를 일치시킨다)
 * No Memory Leak(메모리 누수를 방지한다)
 * No crashes due to stopped Activity(멈춘 Activity 때문에 앱이 충돌하는 일이 없다
 *
 * Observer 패턴을 사용하기 때문에, 데이터의 변화를 실시간으로 구독자에게 통지할 수 있다.
 * Activity / Fragment 의 라이프사이클을 따라 동작하기 때문에, 메모리 릭이 발생하지 않는다.
 * 연결된 컴포넌트의 수명주기가 끝나면 (죽으면) LiveData 도 자동으로 삭제됨
 *
 * 항상 최신 데이터를 유지하기 때문에 기기 회전이 일어나도 View 의 데이터가 날아가지 않는다
 * ViewModel (AAC) 과 함께 사용했을 때의 이야기
 *
 * 주의사항
 *  - 연속적인 값 변경 처리를 해야하고 각각의 값이 유의미한 경우 (유실되어선 안될 경우), LiveData를 따로 두는 편이 좋다
 *
 * */
@HiltViewModel
class LiveDataViewModel @Inject constructor(
) : BaseViewModel() {

    /**
     * ViewModel 내부에서만 값을 변경할 수 있는 _count 변수입니다.
     * 외부에서는 변경할 수 없고, LiveData(count)를 통해서만 접근 가능합니다.
     *
     * 이유:
     * - View와 ViewModel 간 역할 분리를 명확히 하기 위함입니다.
     * - ViewModel은 데이터 변경 및 비즈니스 로직을 처리하고,
     *   View는 데이터 변경을 관찰하고 화면에 표시하는 역할만 해야 합니다.
     *
     * 이렇게 설계하면:
     * - View가 직접 데이터를 수정하는 것을 방지하여 애플리케이션 구조가 안정적이고 유지보수가 쉬워집니다.
     *
     * - 만약 View에서 값을 변경해야 한다면, ViewModel에 정의된 메서드(ex. incrementCount())를 호출하여 처리합니다.
     *  : 정보 은닉(Encapsulation)과 역할 분리를 통해 ViewModel만 비즈니스 로직과 상태 변경을 처리할 수 있도록 보장합니다.
     *  : View는 데이터 변경이 아닌 관찰만 하므로, 앱 구조가 안정적이고 유지보수가 용이해집니다.
     * - 상황에 따라 View에서 값을 설정할 수 있지만, 이 경우 명확한 설계 의도와 책임 분리가 필요합니다.
     */
    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count

    /**
     * LiveData 는 항상 UI 업데이트를 목적
     * setValue
     *  - 동기적으로 즉시 값을 변경
     *  - MainThread에서만 호출
     * MainThread 가 보장될 경우 활용 (MainThread 에서의 값 업데이트)
     * MainThread가 아닌 곳에서 호출 할 경우 런타임 오류 발생
     * 자세한 정보는 setValue 내부 코드 참조 (@MainTrhead 어노테이션 및 assertMainThread 호출)
     */
    fun incrementCountForSetValue() {
        _count.value = (_count.value ?: 0) + 1
    }

    /**
     * PostValue
     *  - 비동기적으로 MainThread에서 값을 변경하는 작업을 예약
     * 내부 함수를 확인하면 메소드 내에서 값 처리를 하지 않고 마지막에 호출함으로써 MainThread로 값 전달
     * ArchTaskExecutor.getInstance().postToMainThread(mPostValueRunnable);
     *
     */
    fun incrementCountForPostValue() {
        _count.postValue((_count.value ?: 0) + 1)
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