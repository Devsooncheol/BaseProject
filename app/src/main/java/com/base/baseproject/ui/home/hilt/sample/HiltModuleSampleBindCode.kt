package com.base.baseproject.ui.home.hilt.sample

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Singleton

/***
 * @Provides
 *
 * - Hilt에서 의존성을 생성하여 주입할 수 있도록 함.
 * - 함수 안에서 객체를 생성하거나 반환할 수 있음.
 * - 반환할 객체를 직접 생성하거나 로직을 통해 제공해야 할 때 사용.
 * - 정적 메서드처럼 동작하므로 abstract class가 아니라 `object`나 `companion object`,
 *   또는 일반 class에서 사용 가능.
 *
 * 예:
 * @Module
 * @InstallIn(SingletonComponent::class)
 * object AppModule {
 *     @Provides
 *     fun provideRepository(api: MyApi): MyRepository {
 *         return MyRepositoryImpl(api)
 *     }
 * }
 ***/

/***
 * @Binds
 *
 * - 이미 생성된 객체(구현체)를 인터페이스에 바인딩할 때 사용.
 * - 구현체는 Hilt가 생성해줄 수 있어야 하며, 단순한 매핑에 적합.
 * - 반환 객체를 직접 만들지 않고, 구현체 → 인터페이스 바인딩만 정의.
 * - 반드시 `abstract` class 또는 interface 안에서 `abstract` 함수로 선언해야 함.
 *
 * 예:
 * @Module
 * @InstallIn(SingletonComponent::class)
 * abstract class AppBindModule {
 *     @Binds
 *     abstract fun bindRepository(
 *         impl: MyRepositoryImpl
 *     ): MyRepository
 * }
 ***/

/***
 * 차이점 요약
 *
 * 1. @Provides
 *    - 구현체를 직접 생성하는 로직이 필요할 때 사용.
 *    - 함수 안에서 객체 생성 가능.
 *    - 유연하지만 더 많은 코드 필요.
 *
 * 2. @Binds
 *    - 인터페이스와 구현체 매핑만 필요할 때 간단하게 사용.
 *    - 생성 로직 없이 간결.
 *    - 구현체는 Hilt가 생성 가능한 구조여야 함 (@Inject constructor 등).
 ***/

// 1. 인터페이스 정의
interface HiltModuleBindInterface {
    fun showString(): String
}

// 2. 구현체 정의
class HiltModuleBindInterfaceImpl @Inject constructor() : HiltModuleBindInterface {
    override fun showString(): String {
        return "HiltModuleBindInterfaceImpl"
    }
}

// 3. Hilt 모듈 정의 - @Binds 사용
@Module
@InstallIn(SingletonComponent::class)
abstract class HiltBindModule {

    @Binds
    @Singleton
    abstract fun bindHiltModuleBindInterface(
        hiltModuleBindInterfaceImpl: HiltModuleBindInterfaceImpl
    ): HiltModuleBindInterface
}