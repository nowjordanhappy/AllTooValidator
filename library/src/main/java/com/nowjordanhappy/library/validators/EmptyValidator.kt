package com.nowjordanhappy.library.validators

import androidx.annotation.StringRes
import com.nowjordanhappy.library.AllTooValidator
import com.nowjordanhappy.library.AllTooValidatorResult

class EmptyValidator(@StringRes private val errorMessageResId: Int) : AllTooValidator<String, String> {
    override fun validate(data: String): AllTooValidatorResult<String> {
        val minLengthValidator = MinLengthValidator(1, errorMessageResId)
        return minLengthValidator.validate(data)
    }
}