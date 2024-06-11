package com.example.nexttogo.network

sealed class ApiResponseResult<out T>{
    data class Success<out T>(val data: T) : ApiResponseResult<T>()
    data class Error(val exception: Throwable) : ApiResponseResult<Nothing>()
    object Loading : ApiResponseResult<Nothing>()
}
