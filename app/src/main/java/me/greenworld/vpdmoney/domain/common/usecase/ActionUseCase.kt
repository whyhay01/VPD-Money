package me.greenworld.vpdmoney.domain.common.usecase

abstract class ActionUseCase<in Param> {

    abstract operator fun invoke(param: Param)
}

// No need to pass "Unit" as a parameter
operator fun ActionUseCase<Unit>.invoke() = this(Unit)
