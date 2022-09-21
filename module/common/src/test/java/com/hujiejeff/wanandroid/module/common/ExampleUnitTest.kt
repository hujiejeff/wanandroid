package com.hujiejeff.wanandroid.module.common

import kotlinx.coroutines.runBlocking
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
        val rep = DataModel.getMainArticles(0)
        println(rep)
    }
}