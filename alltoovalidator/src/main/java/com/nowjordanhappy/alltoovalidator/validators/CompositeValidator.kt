package com.nowjordanhappy.alltoovalidator.validators

import com.nowjordanhappy.alltoovalidator.AllTooValidator
import com.nowjordanhappy.alltoovalidator.AllTooValidatorResult

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