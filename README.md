# AllTooValidator
[![](https://jitpack.io/v/devsideal/ReadMoreOption.svg)](https://jitpack.io/#nowjordanhappy/AllTooValidator)

Validator using Chain of reponsability pattern_
This is a validator in kotlin that uses chain of responsability to add validations for inputs or

- Type some Markdown on the left
- See HTML in the right
- ✨Magic ✨

## How to use
- **Basic Validator**
    This library has basic validators like

    | Name | Description |
    | ------ | ------ |
    | MinLengthValidator | check the min length of a string |
    | MaxLengthValidator | check the max length of a string |
    | EqualsLengthValidator | check if length of a string is equal a value|
    | EmptyValidator | Shortcut, use MinLengthValidator to check if string is empty |
    | MinValueValidator | use validation for min value, this type should be comparable as: int, double, etc |
    
    How to use:
    
    ```kotlin
    //***** using Validator only ******
    //validate identification number
    //8 characters
    val identificationNumber = binding.identificationDocumentET.text?.toString() ?: ""
    val identificationNumberValidator = EqualsLengthValidator(expectedLength = 8, errorMessageResId = R.string.error_invalid_identification_number)
    val identificationNumberResult = identificationNumberValidator.validate(identificationNumber)
    ```
- **String Builder Validator**
    The StringValidatorBuilder helps to add multiple validations at the same time, it stops when one validation is false so the **order is important**:
    
    ```kotlin
    //***** using StringValidatorBuilder ******
    //phone
    //9-11 characters
    val phone = binding.phoneET.text?.toString() ?: ""
    //Using a builder
    val phoneValidator = StringValidatorBuilder()
            .addValidator(EmptyValidator(errorMessageResId = R.string.error_empty_phone))
            .addValidator(MinLengthValidator(minLength = 9, errorMessageResId = R.string.error_invalid_phone_number))
            .addValidator(MaxLengthValidator(maxLength = 12, errorMessageResId = R.string.error_invalid_phone_number))
            .build()
    val phoneResult = phoneValidator.validate(phone)
    ```

- **Custom Validator**
    For this we use AdultAgeValidator. For create a custom library it should extend from AllTooValidator, and specify the enter input and the output intput. In this case, we use a string and return a int value.

    ```kotlin
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
    ```

## Check validation
To check if the validation fails, we show check using the AllTooValidatorResult, in case of success we can we the value, and for failure, we get the errorMessageResId:

    ```kotlin
    sealed class AllTooValidatorResult<out T> {
        data class Success<out T>(val result: T) : AllTooValidatorResult<T>()
        //data class Failure(@StringRes val errorMessageResId: Int) : AllTooValidatorResult<Nothing>()
        data class Failure(val errorMessageResId: Int) : AllTooValidatorResult<Nothing>()
    }
    ```
And for checking we could do this:
    
    
    ```kotlin
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
    ```
## Dependency
- Add the dependencies to your gradle files:

#### Step 1. Add it in your root build.gradle at the end of repositories
```gradle
   allprojects {
       repositories {
    	...
    	maven { url 'https://jitpack.io' }
    	}
    }
```

#### Step 2. Add the dependency
```gradle
    dependencies {
        implementation 'com.github.nowjordanhappy:AllTooValidator:0.1.1'
     }
```

## License
MIT License

Copyright (c) 2023 Jordan R. A.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.


**Free Software**
