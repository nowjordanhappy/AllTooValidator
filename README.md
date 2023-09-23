# AllTooValidator
[![](https://jitpack.io/v/devsideal/ReadMoreOption.svg)](https://jitpack.io/#nowjordanhappy/AllTooValidator)

Validator using the Chain of Responsibility pattern.
This is a Kotlin validator that uses the Chain of Responsibility pattern to add validations for inputs or ...

## How to Use
- **Basic Validator**

  This library includes basic validators like:

  | Name                | Description                              |
      | ------------------- | ---------------------------------------- |
  | MinLengthValidator  | Checks the minimum length of a string   |
  | MaxLengthValidator  | Checks the maximum length of a string   |
  | EqualsLengthValidator | Checks if the length of a string is equal to a value |
  | EmptyValidator      | A shortcut to check if a string is empty |
  | MinValueValidator   | Use this validation for minimum value; this type should be comparable (e.g., int, double, etc.) |

  Usage:

    ```kotlin
    //***** Using Validator Only ******
    // Validate identification number (8 characters)
    val identificationNumber = binding.identificationDocumentET.text?.toString() ?: ""
    val identificationNumberValidator = EqualsLengthValidator(expectedLength = 8, errorMessageResId = R.string.error_invalid_identification_number)
    val identificationNumberResult = identificationNumberValidator.validate(identificationNumber)
    ```

- **String Builder Validator**

  The `StringValidatorBuilder` helps to add multiple validations at the same time. It stops when one validation fails, so the order is important:

    ```kotlin
    //***** Using StringValidatorBuilder ******
    // Phone (9-11 characters)
    val phone = binding.phoneET.text?.toString() ?: ""
    // Using a builder
    val phoneValidator = StringValidatorBuilder()
            .addValidator(EmptyValidator(errorMessageResId = R.string.error_empty_phone))
            .addValidator(MinLengthValidator(minLength = 9, errorMessageResId = R.string.error_invalid_phone_number))
            .addValidator(MaxLengthValidator(maxLength = 12, errorMessageResId = R.string.error_invalid_phone_number))
            .build()
    val phoneResult = phoneValidator.validate(phone)
    ```

- **Custom Validator**

  For this, we use `AdultAgeValidator`. To create a custom validator, it should extend from `AllTooValidator` and specify the input and output types. In this case, we use a string as input and return an integer value.

    ```kotlin
    class AdultAgeValidator: AllTooValidator<String, Int> {
        override fun validate(data: String): AllTooValidatorResult<Int> {
            val emptyValidator = EmptyValidator(R.string.error_empty_age)
            val emptyValidationResult = emptyValidator.validate(data)

            if (emptyValidationResult is AllTooValidatorResult.Failure) {
                return emptyValidationResult
            }

            data.toIntOrNull()?.let { age ->
                val minValueValidator = MinValueValidator(18, R.string.error_age_must_be_adult)
                return minValueValidator.validate(age)
            } ?: run {
                return AllTooValidatorResult.Failure(R.string.error_invalid_age)
            }
        }
    }
    ```

## Check Validation

To check if the validation fails, we can use `AllTooValidatorResult`. In case of success, we can get the value, and for failure, we get the `errorMessageResId`:

```kotlin
sealed class AllTooValidatorResult<out T> {
    data class Success<out T>(val result: T) : AllTooValidatorResult<T>()
    data class Failure(val errorMessageResId: Int) : AllTooValidatorResult<Nothing>()
}