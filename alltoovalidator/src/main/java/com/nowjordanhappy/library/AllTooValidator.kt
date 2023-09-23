package com.nowjordanhappy.library

interface AllTooValidator<T, R> {
    fun validate(data: T): AllTooValidatorResult<R>
}