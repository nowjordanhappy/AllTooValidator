package com.nowjordanhappy.alltoovalidator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nowjordanhappy.alltoovalidator.custom_validators.AdultAgeValidator
import com.nowjordanhappy.alltoovalidator.databinding.ActivityMainBinding
import com.nowjordanhappy.alltoovalidator.validators.EmptyValidator
import com.nowjordanhappy.alltoovalidator.validators.EqualsLengthValidator
import com.nowjordanhappy.alltoovalidator.validators.MaxLengthValidator
import com.nowjordanhappy.alltoovalidator.validators.MinLengthValidator
import com.nowjordanhappy.alltoovalidator.validators.StringValidatorBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initParams()
    }

    private fun initParams(){
        binding.validate.setOnClickListener {
            validateInputs()
        }
    }

    private fun validateInputs() {
        //***** using Validator only ******
        //validate identification number
        //8 characters
        val identificationNumber = binding.identificationDocumentET.text?.toString() ?: ""
        val identificationNumberValidator = EqualsLengthValidator(expectedLength = 8, errorMessageResId = R.string.error_invalid_identification_number)
        val identificationNumberResult = identificationNumberValidator.validate(identificationNumber)

        if (identificationNumberResult is AllTooValidatorResult.Failure){
            binding.identificationDocumentTIL.error = getString(identificationNumberResult.errorMessageResId)
        }else{
            binding.identificationDocumentTIL.error = null
        }
        //***** using StringValidatorBuilder ******
        //phone
        //9-11 characters
        val phone = binding.phoneET.text?.toString() ?: ""
        //Using a builder
        val phoneValidator = StringValidatorBuilder()
            .addValidator(EmptyValidator(errorMessageResId = R.string.error_empty_phone))
            .addValidator(MinLengthValidator(minLength = 9, errorMessageResId = R.string.error_invalid_phone_number))
            .addValidator(MaxLengthValidator(maxLength = 11, errorMessageResId = R.string.error_invalid_phone_number))
            .build()
        val phoneResult = phoneValidator.validate(phone)

        if (phoneResult is AllTooValidatorResult.Failure){
            binding.phoneTIL.error = getString(phoneResult.errorMessageResId)
        }else{
            binding.phoneTIL.error = null
        }

        //***** using custom Validator ******
        //years old
        //should be >= 18
        val age = binding.yearsOldET.text?.toString() ?: ""
        val ageValidator = AdultAgeValidator()
        val ageResult = ageValidator.validate(age)

        when (ageResult) {
            is AllTooValidatorResult.Failure -> {
                binding.yearsOldTIL.error = getString(ageResult.errorMessageResId)
            }
            is AllTooValidatorResult.Success -> {
                //getting value as Int
                //val ageValue = ageResult.result
                binding.yearsOldTIL.error = null
            }
        }
    }
}