package com.bagadesh.domain

import com.bagadesh.domain.base.BaseUseCase

class TestUseCase : BaseUseCase<String,Int>() {

    override suspend fun runBaseUseCase(param: String): ResultData<Int> {
        return ResultData.Success(1)
    }
}