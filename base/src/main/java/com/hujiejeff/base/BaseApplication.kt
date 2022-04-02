package com.hujiejeff.base

import android.app.Application
import com.hujiejeff.base.data.DataRepository

open class BaseApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DataRepository.init(this)
    }
}