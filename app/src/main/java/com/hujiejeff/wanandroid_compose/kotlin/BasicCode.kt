package com.hujiejeff.wanandroid_compose.kotlin

fun main() {
    runInlineCode(
        inlineCode = {
            //内联，return全局返回
            return
        },
        noinlineCode = {
            //不内联，return 局部返回
            return@runInlineCode
        },
        crossInLineCode = {
            //内联，return 局部返回
            return@runInlineCode
        })
}

inline fun runInlineCode(
    inlineCode: () -> Unit,
    noinline noinlineCode: () -> Unit,
    crossinline crossInLineCode: () -> Unit
) {
    inlineCode()
    noinlineCode()
    val runnable = Runnable {
        crossInLineCode()
    }
    runnable.run()
}

inline fun <reified T> getGenericType() = T::class.java