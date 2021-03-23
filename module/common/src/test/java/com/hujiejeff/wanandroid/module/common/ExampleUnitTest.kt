package com.hujiejeff.wanandroid.module.common

import com.hujiejeff.wanadnroid.module.base.data.remote.GlobalHttp
import kotlinx.coroutines.runBlocking
import network.WanAndroidApi
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
        val rep = GlobalHttp.getInstance().getApi(WanAndroidApi::class.java).getMainArticles(0)
        println(rep)
    }
}