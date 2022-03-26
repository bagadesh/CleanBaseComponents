package com.bagadesh.cleanbasecomponents

import com.bagadesh.domain.TestUseCase
import kotlinx.coroutines.runBlocking

class TestClass {
    fun execute() {
        runBlocking {
            TestUseCase().execute("") {
                onComplete {

                }

                onError {

                }
            }
        }
    }
}