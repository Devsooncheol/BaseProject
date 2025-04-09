package com.base.baseproject.ui.home.hilt.sample


/**
 * 의존성 주입(Dependency Injection, DI)
 *  - 객체가 직접 의존 대상을 생성하지 않고 외부에서 주입받아 사용하는 패턴
 *  - 목적: 모듈 간 결합도를 낮춰 유연하고 테스트 가능한 구조 설계
 *  - 방식: 생성자 주입, 필드 주입, 메서드 주입
 *
 * 장점:
 *  - 코드 재사용성 증가
 *  - 테스트 용이 (Mock 객체를 주입 가능)
 *  - 확장성 향상 (새 구현체가 있어도 기존 클래스 변경 불필요)
 *
 * 단점:
 *  - 구조가 복잡해질 수 있음
 *  - 구성(configuration)에 대한 명확한 이해 필요
 */
fun main() {
    /** 직접 객체 생성 - 강한 결합 */
    val car = Car()
    car.start()

    /** 의존성 주입 - 느슨한 결합 (Loose Coupling) */
    val engine = Engine()
    val carWithEngine = NewCar(engine)
    carWithEngine.start()

    val gas = Gas()
    val gasCar = NewCar(gas)
    gasCar.start()

    val electricEngine = ElectricEngine()
    val electricCar = NewCar(electricEngine)
    electricCar.start()

    /** 테스트용 Mock 주입 */
    val mockEngine = MockEngine()
    val testCar = NewCar(mockEngine)
    testCar.start()

    /** 동적 주입을 흉내낸 수동 DI 팩토리 함수 사용 */
    val injectedCar = provideCar("electric")
    injectedCar.start()
}


/**
 * 강한 결합 예시: Car 클래스 내부에서 Engine 객체를 직접 생성함
 *  - Engine에 강하게 의존함 (결합도가 높음)
 *  - 다른 타입의 Engine으로 대체하려면 Car를 수정해야 함
 */
class Car {
    private val engine = Engine()
    fun start() {
        engine.start()
    }
}

/**
 * 의존성 주입 예시: CarModule을 생성자로 받아 사용
 *  - 다양한 구현체(Gas, Electric 등)를 동적으로 주입 가능
 */
class NewCar(private val carModule: CarModule) {
    fun start() {
        println("차량 시동 시도 중...")
        carModule.start()
    }
}

/**
 * CarModule 인터페이스
 *  - 다양한 엔진 타입을 추상화
 */
interface CarModule {
    fun start()
}

/** 기본 엔진 구현 */
class Engine : CarModule {
    override fun start() {
        println("Engine started")
    }
}

/** 가솔린 엔진 구현 */
class Gas : CarModule {
    override fun start() {
        println("Gasoline engine started")
    }
}

/** 전기 엔진 구현 */
class ElectricEngine : CarModule {
    override fun start() {
        println("Electric engine started")
    }
}

/** 테스트용 목(Mocking) 객체 */
class MockEngine : CarModule {
    override fun start() {
        println("MockEngine started - 테스트 중")
    }
}

/**
 * 수동 의존성 주입 팩토리 함수
 *  - 문자열로 구현체를 선택해 반환
 *  - 실제 DI 프레임워크 없이도 동적 인스턴스 주입 가능
 */
fun provideCar(type: String): NewCar {
    val engine: CarModule = when (type) {
        "gas" -> Gas()
        "electric" -> ElectricEngine()
        else -> Engine()
    }
    return NewCar(engine)
}

