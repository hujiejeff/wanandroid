package com.hujiejeff.wanadnroid.module.base

import com.hujiejeff.wanadnroid.module.base.utils.getSuperClassGenericClass
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
    fun test() {
        println("xxx")
        val a = B()
        a.printGenericClass()
    }
}

class B: A<String, Int>() {

}
open class A<V,T> {
    fun printGenericClass() {
        println(getSuperClassGenericClass<V>(this, 1))
    }
}