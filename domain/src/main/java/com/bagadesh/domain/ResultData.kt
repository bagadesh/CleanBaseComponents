package com.bagadesh.domain

sealed class ResultData<T> {

    data class Success<T>(val result: T) : ResultData<T>()

    data class Error<T>(val exception: Exception, val errorMessage: String = "") : ResultData<T>()

}