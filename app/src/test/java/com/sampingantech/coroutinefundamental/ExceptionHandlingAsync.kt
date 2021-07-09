package com.sampingantech.coroutinefundamental

import kotlinx.coroutines.*
import org.junit.Test

class ExceptionHandlingAsync {

    private val exceptionHandling = CoroutineExceptionHandler { coroutineContext, throwable ->
        print("got error: ${throwable.message}")
    }
    private val someScope = GlobalScope

    @Test
    fun `direct throw exception in async`() {
        runBlocking {
            val deferred: Deferred<String> = async {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            println(deferred.await())
            println("finish")
        }
    }

    @Test
    fun `direct throw exception in async with try-catch`() {
        runBlocking {
            val deferred: Deferred<String> = async {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            try {
                println(deferred.await())
            } catch (e: IllegalArgumentException) {
                println("catch error : ${e.message}")
            }
            println("finish")
        }
    }

    @Test
    fun `cancellation exception in async`() {
        runBlocking {
            val deferred: Deferred<String> = async {
                println("start job")
                throw CancellationException("cancel")
            }
            println(deferred.await())
            println("finish")
        }
    }

    @Test
    fun `cancellation exception with handling in async`() {
        runBlocking {
            val deferred: Deferred<String> = async(exceptionHandling) {
                println("start job")
                throw CancellationException("cancel")
            }
            println(deferred.await())
            println("finish")
        }
    }

    @Test
    fun `exception with handling in launch`() {
        runBlocking {
            val deferred: Deferred<String> = async(exceptionHandling) {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            println(deferred.await())
            println("finish")
        }
    }

    @Test
    fun `exception with handling in async with scope`() {
        runBlocking {
            val deferred: Deferred<String> = someScope.async(exceptionHandling) {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            println(deferred.await())
            println("finish")
        }
    }

    @Test
    fun `exception with handling in async with try-catch`() {
        runBlocking {
            val deferred: Deferred<String> = async {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            try {
                println(deferred.await())
            } catch (e: IllegalArgumentException) {
                println("catch error : ${e.message}")
            }
            println("finish")
        }
    }

    @Test
    fun `cancellation exception in async with scope with try-catch`() {
        runBlocking {
            val deferred: Deferred<String> = someScope.async {
                println("start job")
                throw CancellationException("throw IllegalArgumentException from launch")
            }
            try {
                println(deferred.await())
            } catch (e: IllegalArgumentException) {
                println("catch error : ${e.message}")
            }
            println("finish")
        }
    }

    @Test
    fun `direct throw exception in async with scope with try-catch`() {
        runBlocking {
            val deferred: Deferred<String> = someScope.async {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            try {
                println(deferred.await())
            } catch (e: IllegalArgumentException) {
                println("catch error : ${e.message}")
            }
            println("finish")
        }
    }

    @Test
    fun `direct throw exception in async with scope with runCatching`() {
        runBlocking {
            val deferred: Deferred<String> = someScope.async(exceptionHandling) {
                println("start job")
                throw IllegalArgumentException("throw IllegalArgumentException from launch")
            }
            runCatching { deferred.await() }
                .onFailure { println("catching run: ${it.message}") }
                .onSuccess { println(it) }
            println("finish")
        }
    }
}