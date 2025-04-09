package com.base.baseproject.ui.home.hilt

import timber.log.Timber
import javax.inject.Inject




/**
 * 1. @Inject constructor
 *  - Hilt가 객체를 생성하고 의존성 그래프에 자동 등록
 *
 *   ⦿ Field Injection
 *     - @AndroidEntryPoint 클래스 내부에서 @Inject lateinit var 로 주입
 *
 *   ⦿ Constructor Injection
 *     - 생성자에 @Inject 붙여 의존성 주입
 *     - 장점: 의존성 명확, 테스트 용이, 컴파일 타임 검증
 *     -  예외: Interface는 생성자 주입 불가
 *         → 어떤 구현체를 주입해야 하는지 Hilt가 판단할 수 없음
 *
 * 2. Hilt Module 사용 (@Module)
 *  - Interface나 외부 라이브러리 객체 등 Hilt가 직접 생성할 수 없는 경우 사용
 *  */


/**
 * hiltFiledInjectTest 클래스는 Hilt를 통해 의존성 주입이 가능한 객체임을 나타내기 위해
 * 생성자에 @Inject 어노테이션을 사용
 *
 * 이 클래스는 Hilt의 Component(@AndroidEntryPoint 등) 내부에서 주입받아 사용
 */
class HiltFiledInject @Inject constructor() {
    fun showTestLog() {
        Timber.i("##hiltTest hiltInjectTest Class Test")
    }
}


/**
 * 아래는 의존성 주입을 사용하는 AType, BType 클래스입니다.
 *
 * - HiltConstructorInjectBTypeClass:
 *   @Inject 어노테이션이 생성자에 붙어 있어, Hilt가 이 클래스의 인스턴스를 생성하고 주입할 수 있도록 표시
 *
 * - HiltConstructorInjectATypeClass:
 *   생성자에서 HiltConstructorInjectBTypeClass 객체를 주입
 *   이처럼 생성자를 통해 다른 객체를 주입받는 방식을 "Constructor Injection(생성자 주입)"
 *
 * Constructor Injection의 장점:
 * - 어떤 의존성이 필요한지 클래스 정의만 봐도 쉽게 파악
 * - Hilt가 컴파일 시점에 주입 가능한 객체인지 검증해주기 때문에 안정적
 * - 단위 테스트 시 의존성을 쉽게 교체(mock)
 */
class HiltConstructorInjectATypeClass @Inject constructor(
    private val hiltConstructorInjectBTypeClass: HiltConstructorInjectBTypeClass
) {
    fun showTestLog() {
        Timber.i("##hiltTest ATypeClass Class Test")
        Timber.i("##hiltTest --- BTypeClass Call")
        hiltConstructorInjectBTypeClass.showTestLog()
    }
}

class HiltConstructorInjectBTypeClass @Inject constructor() {
    fun showTestLog() {
        Timber.i("##hiltTest BTypeClass Class Test")
    }
}


/**
 * Hilt에서 Interface Constructor Injection 예외 정리
 *  - 아래 예시는 Hilt를 사용하여 Interface를 Constructor Injection 하려는 상황
 *  - ClassA는 생성자에서 AInterface를 주입받고 있고,
 *  - ClassB는 AInterface를 구현하고 있다.
 *
 * 발생하는 문제
 *  - Hilt는 AInterface를 주입하려고 할 때 어떤 구현체(ClassB)를 사용해야 할지 알 수 없음
 *  - 따라서 다음과 같은 컴파일 에러가 발생
 *      @Inject constructor cannot be applied to interface AInterface
 *      Hilt does not know how to provide an instance of AInterface
 *
 * 발생하는 이유
 *  - Interface는 인스턴스를 만들 수 없는 추상 타입
 *  - Hilt는 AInterface 타입을 생성하려고 할 때 "어떤 구현체를 써야 하는지" 몰라서 실패
 *  - 즉, interface와 구현체의 관계를 Hilt에 명시적으로 알려줘야 함
 *
 * ClassA를 호출 할 경우에 컴파일 에러 발생
 *  - Hilt는 사용되는 시점에 필요한 의존성 그래프를 분석
 *  - ClassA를 어디에서도 주입하거나 호출하지 않으면, Hilt는 AInterface가 필요하다는 사실 자체를 인지하지 못함
 *
 * 해결방법
 *  - Hilt 모듈에서 @Binds 또는 @Provides로 어떤 구현체를 사용할지 정의
 * */
interface AInterface {
    fun showString(): String
}

class ClassA @Inject constructor(
    private val bTypeVal: AInterface
) {
    fun doTestA(): String {
        return bTypeVal.showString()
    }
}

class ClassB @Inject constructor() : AInterface {
    override fun showString(): String = "get Go!"
}