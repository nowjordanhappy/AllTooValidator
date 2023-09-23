package com.nowjordanhappy.alltoovalidator.validators

import androidx.annotation.StringRes
import com.nowjordanhappy.alltoovalidator.AllTooValidator
import com.nowjordanhappy.alltoovalidator.AllTooValidatorResult

class MaxLengthValidator(private val maxLength: Int, @StringRes private val errorMessageResId: Int) :
    AllTooValidator<String, String> {
    override fun validate(data: String): AllTooValidatorResult<String> {
        return if (data.length <= maxLength) {
            AllTooValidatorResult.Success(data)
        } else {
            AllTooValidatorResult.Failure(errorMessageResId)
        }
    }
}