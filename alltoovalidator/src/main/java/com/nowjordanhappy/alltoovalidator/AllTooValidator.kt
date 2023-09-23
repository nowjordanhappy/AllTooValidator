package com.nowjordanhappy.alltoovalidator

interface AllTooValidator<T, R> {
    fun validate(data: T): AllTooValidatorResult<R>
}