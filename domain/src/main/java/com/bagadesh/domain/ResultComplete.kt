package com.bagadesh.domain

class ResultComplete<T> {

    private var onComplete: (ResultData.Success<T>) -> Unit = {}

    private var onError: (ResultData.Error<T>) -> Unit = {}

    fun onComplete(onComplete: (ResultData.Success<T>) -> Unit) {
        this.onComplete = onComplete
    }

    fun onError(onError: (ResultData.Error<T>) -> Unit) {
        this.onError = onError
    }

    //Invoke methods
    fun invokeOnComplete(result: ResultData.Success<T>) {
        onComplete.invoke(result)
    }

    fun invokeOnError(result: ResultData.Error<T>) {
        onError.invoke(result)
    }
}