package com.base.baseproject.ui.home.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Qualifier

/**
 * @Provides가 필요한 상황 요약
 * Hilt에서 의존성 주입은 기본적으로 @Inject로 대부분 처리할 수 있지만, 다음과 같은 경우에는 @Provides를 사용
 *  1. 외부 라이브러리의 객체
 *      우리가 직접 생성자를 @Inject로 제어할 수 없을 때
 *  2. 빌더 패턴을 통해 객체를 생성
 *      Retrofit.Builder().create(), Room.databaseBuilder() 등
 *  3. 특정 파라미터가 필요한 객체를 주입
 *      예: 생성자에 String 같은 기본형 타입이 필요한 경우 (Hilt는 기본형은 자동 생성 못함)
 * */

// 테스트용 인터페이스 정의
interface TestInterface {
    fun showString(): String
}

// TestInterface 구현체 1
// - 생성자에서 String 타입 의존성을 주입받음
class HiltModuleInjectBTypeClass @Inject constructor(
    private val dependencyCTypeString: String
) : TestInterface {
    override fun showString(): String {
        return "HiltModuleInjectBTypeClass $dependencyCTypeString!"
    }
}

// TestInterface 구현체 2
// - 생성자에서 String 타입 의존성을 주입받음
class HiltModuleInjectB2TypeClass @Inject constructor(
    private val dependencyCTypeString: String
) : TestInterface {
    override fun showString(): String {
        return "HiltModuleInjectB2TypeClass $dependencyCTypeString!"
    }
}


// TestInterface를 주입받는 최상위 클래스
// - 두 개의 서로 다른 구현체를 각각 Qualifier로 주입
class HiltModuleInjectATypeClass @Inject constructor(
    @TestModule.TestInterface1 private val testInterface1: TestInterface,
    @TestModule.TestInterface2 private val testInterface2: TestInterface
) {
    fun showTestLog(): String {
        return testInterface1.showString()
    }

    fun showTestLog2(): String {
        return testInterface2.showString()
    }
}

// Hilt Module 정의
@Module
@InstallIn(ActivityComponent::class)
object TestModule {

    // Qualifier 어노테이션 정의: 동일한 타입의 의존성을 구분할 때 사용
    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TestInterface1

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class TestInterface2


    // 1. 기본형 타입(String)은 생성자 @Inject로 주입 불가 → @Provides 필요
    @Provides
    fun provideCTypeString(): String {
        return "c String"
    }

    // TestInterface 구현체 주입
    // - 생성자에 String 의존성이 필요하므로, 직접 생성하여 주입
    // - TestInterface1으로 구분되는 구현체 제공
    @TestInterface1
    @Provides
    fun provideTestInterfaceB(cString: String): TestInterface {
        return HiltModuleInjectBTypeClass(cString)
    }

    // - TestInterface2로 구분되는 다른 구현체 제공
    @TestInterface2
    @Provides
    fun provideTestInterfaceB2(cString: String): TestInterface {
        return HiltModuleInjectB2TypeClass(cString)
    }
}