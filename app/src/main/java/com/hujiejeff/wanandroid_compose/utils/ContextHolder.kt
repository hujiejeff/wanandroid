package com.hujiejeff.wanandroid_compose.utils

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object ContextHolder {
    lateinit var context: Context
}