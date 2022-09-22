package com.hujiejeff.wanandroid.module.common

import kotlinx.coroutines.*
import network.DataModel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testApi() = runBlocking {
        val coroutineScopeA = CoroutineScope(Dispatchers.Default)

            coroutineScope {
                val rep = DataModel.getMainArticles(0)
                println(rep)
            }
            println("end")

        println("over")
        withContext(Dispatchers.Default) {}
        val result = async {
            delay(1000)
            2
        }
        println("My job is ${coroutineContext[Job]}")
        async {

        }
        joinAll()
    }


    @Test
    fun test() = runBlocking {
       test1()
    }
}

fun test1() = runBlocking { // this: CoroutineScope
    launch {
        delay(200L)
        println("Task from runBlocking")
    }

    coroutineScope { // 创建一个协程作用域
        launch {
            delay(500L)
            println("Task from nested launch")
        }

        delay(100L)
        println("Task from coroutine scope") // 这一行会在内嵌 launch 之前输出
    }

    println("Coroutine scope is over") // 这一行在内嵌 launch 执行完毕后才输出
}