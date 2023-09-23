package com.nowjordanhappy.library

//import androidx.annotation.StringRes

sealed class AllTooValidatorResult<out T> {
    data class Success<out T>(val result: T) : AllTooValidatorResult<T>()
    //data class Failure(@StringRes val errorMessageResId: Int) : AllTooValidatorResult<Nothing>()
    data class Failure(val errorMessageResId: Int) : AllTooValidatorResult<Nothing>()
}