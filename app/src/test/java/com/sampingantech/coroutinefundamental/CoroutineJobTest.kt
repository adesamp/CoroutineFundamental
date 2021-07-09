package com.sampingantech.coroutinefundamental

import kotlinx.coroutines.*
import org.junit.Test

class CoroutineJobTest {

    @Test
    fun `Join Test`() {
        runBlocking {
            val job : Job = launch {
                delay(2_000)
                println(Thread.currentThread().name)
            }
            println("start")
            job.join()
        }
    }

    @Test
    fun `JoinAll Test`() {
        runBlocking {
            val job1 : Job = launch { println(Thread.currentThread().name) }
            val job2 : Job = launch { println(Thread.currentThread().name) }
            val job3 : Job = launch { println(Thread.currentThread().name) }
            joinAll(job1,job2,job3)
        }
    }

    @Test
    fun `direct launch Test`() {
        runBlocking {
            launch { println(Thread.currentThread().name) }
        }
    }
}