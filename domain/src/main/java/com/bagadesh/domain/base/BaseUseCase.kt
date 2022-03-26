package com.bagadesh.domain.base

import com.bagadesh.domain.ResultComplete
import com.bagadesh.domain.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class BaseUseCase<Param, Result> {

    private val ioScope = Dispatchers.IO

    private val defaultScope = Dispatchers.Default

    private val mainScope = Dispatchers.Main

    abstract suspend fun runBaseUseCase(param: Param): ResultData<Result>

    suspend fun execute(param: Param): ResultData<Result> {
        return try {
            runBaseUseCase(param)
            withContext(ioScope) { runBaseUseCase(param) }
        } catch (exception: Exception) {
            ResultData.Error(exception = exception, errorMessage = "Unknown Exception")
        }
    }

    suspend fun execute(param: Param, resultComplete: ResultComplete<Result>.() -> Unit) {
        val completer = ResultComplete<Result>()
        resultComplete(completer)
        try {
            when (val result = withContext(ioScope) { runBaseUseCase(param) }) {
                is ResultData.Error -> completer.invokeOnError(result)
                is ResultData.Success -> completer.invokeOnComplete(result)
            }

        } catch (exception: Exception) {
            completer.invokeOnError(ResultData.Error(exception = exception, errorMessage = "Unknown Exception"))
        }
    }

}