package com.nowjordanhappy.alltoovalidator.validators

import androidx.annotation.StringRes
import com.nowjordanhappy.alltoovalidator.AllTooValidator
import com.nowjordanhappy.alltoovalidator.AllTooValidatorResult

class MinValueValidator<T : Comparable<T>>(private val minValue: T, @StringRes private val errorMessageResId: Int) :
    AllTooValidator<T, T> {
    override fun validate(data: T): AllTooValidatorResult<T> {
        return if (data >= minValue) {
            AllTooValidatorResult.Success(data)
        } else {
            AllTooValidatorResult.Failure(errorMessageResId)
        }
    }
}