package me.greenworld.vpdmoney.domain.common.usecase

abstract class SuspendUseCase<in Param, out Output> {

    abstract suspend operator fun invoke(param: Param): Output
}

suspend operator fun<Output> SuspendUseCase<Unit, Output>.invoke() = this(Unit)