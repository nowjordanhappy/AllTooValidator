package com.nowjordanhappy.library.validators

import androidx.annotation.StringRes
import com.nowjordanhappy.library.AllTooValidator
import com.nowjordanhappy.library.AllTooValidatorResult

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