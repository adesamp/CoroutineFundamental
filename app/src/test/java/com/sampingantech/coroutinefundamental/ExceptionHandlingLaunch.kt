package com.sampingantech.coroutinefundamental

import kotlinx.coroutines.*
import org.junit.Test

class ExceptionHandlingLaunch {

    private val exceptionHandling = CoroutineExceptionHandler { coroutineContext, throwable ->
        print("got error: ${throwable.message}")
    }

    @DelicateCoroutinesApi
    private val someScope = GlobalScope

    @Test
    fun `direct throw exception in launch`() {
        runBlocking {
            val job = launch {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            job.join()
            println("finish")
        }
    }

    @Test
    fun `direct throw exception in launch with try-catch`() {
        runBlocking {
            val job = launch {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            try {
                job.join()
            } catch (e: IllegalArgumentException) {
                println("catch error : ${e.message}")
            }
            println("finish")
        }
    }

    @Test
    fun `cancellation exception in launch`() {
        runBlocking {
            val job = launch {
                println("start job")
                throw CancellationException("cancel")
            }
            job.join()
            println("finish")
        }
    }

    @Test
    fun `cancellation exception with handling in launch`() {
        runBlocking {
            val job = launch(exceptionHandling) {
                println("start job")
                throw CancellationException("cancel")
            }
            job.join()
            println("finish")
        }
    }

    @Test
    fun `exception with handling in launch`() {
        runBlocking {
            val job = launch(exceptionHandling) {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            job.join()
            println("finish")
        }
    }

    @Test
    fun `exception with handling in launch with scope`() {
        runBlocking {
            val job = someScope.launch(exceptionHandling) {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            job.join()
            println("finish")
        }
    }
}