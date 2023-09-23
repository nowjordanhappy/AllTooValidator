package com.nowjordanhappy.alltoovalidator.validators

import com.nowjordanhappy.alltoovalidator.AllTooValidator

class StringValidatorBuilder {
    private val validators = mutableListOf<AllTooValidator<String, String>>()

    fun addValidator(validator: AllTooValidator<String, String>): StringValidatorBuilder {
        validators.add(validator)
        return this
    }

    fun build(): CompositeValidator<String, String> {
        return CompositeValidator(validators)
    }
}