package com.sampingantech.coroutinefundamental

import kotlinx.coroutines.*
import org.junit.Test

class CoroutineAsyncTest {

    @Test
    fun `Await Test`() {
        runBlocking {
            val deferred: Deferred<String> = async { return@async Thread.currentThread().name }
            println(deferred.await())
        }
    }

    @Test
    fun `AwaitAll Test`() {
        runBlocking {
            val deferred1: Deferred<Int> = async { 1 }
            val deferred2: Deferred<Int> = async { 2 }
            val deferred3: Deferred<Int> = async { 3 }
            println(awaitAll(deferred1, deferred2, deferred3).sum())
        }
    }
}