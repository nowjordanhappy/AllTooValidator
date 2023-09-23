package com.nowjordanhappy.alltoovalidator.custom_validators

import com.nowjordanhappy.alltoovalidator.R
import com.nowjordanhappy.alltoovalidator.AllTooValidator
import com.nowjordanhappy.alltoovalidator.AllTooValidatorResult
import com.nowjordanhappy.alltoovalidator.validators.EmptyValidator
import com.nowjordanhappy.alltoovalidator.validators.MinValueValidator

class AdultAgeValidator: AllTooValidator<String, Int> {
    override fun validate(data: String): AllTooValidatorResult<Int> {
        val emptyValidator = EmptyValidator(R.string.error_empty_age)
        val emptyValidationResult = emptyValidator.validate(data)

        if (emptyValidationResult is AllTooValidatorResult.Failure) {
            return emptyValidationResult
        }

        data.toIntOrNull()?.let {age->
            val minValueValidator = MinValueValidator(18, R.string.error_age_must_be_adult)
            return minValueValidator.validate(age)
        } ?: run{
            return AllTooValidatorResult.Failure(R.string.error_invalid_age)
        }
    }
}