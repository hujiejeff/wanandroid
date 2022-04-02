package com.hujiejeff.base

import android.app.Application
import com.hujiejeff.base.data.DataRepository

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DataRepository.init(this)
    }
}