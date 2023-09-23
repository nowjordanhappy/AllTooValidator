package com.nowjordanhappy.library.validators

import androidx.annotation.StringRes
import com.nowjordanhappy.library.AllTooValidator
import com.nowjordanhappy.library.AllTooValidatorResult

class EqualsLengthValidator(
    private val expectedLength: Int,
    @StringRes private val errorMessageResId: Int
) : AllTooValidator<String, String> {
    override fun validate(data: String): AllTooValidatorResult<String> {
        return if (data.length == expectedLength) {
            AllTooValidatorResult.Success(data)
        } else {
            AllTooValidatorResult.Failure(errorMessageResId)
        }
    }
}