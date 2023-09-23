package com.nowjordanhappy.library.validators

import com.nowjordanhappy.library.AllTooValidator
import com.nowjordanhappy.library.AllTooValidatorResult

class CompositeValidator<T, R>(private val validators: List<AllTooValidator<T, R>>) : AllTooValidator<T, R> {
    override fun validate(data: T): AllTooValidatorResult<R> {
        for (validator in validators) {
            val result = validator.validate(data)
            if (result is AllTooValidatorResult.Failure) {
                return result
            }
        }
        return AllTooValidatorResult.Success(data as R)
    }
}