package com.hujiejeff.wanadnroid.module.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.hujiejeff.wanadnroid.module.base.data.DataRepository

class MyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openDebug()
            ARouter.openLog()
        }
        ARouter.init(this)
        DataRepository.init(this)
    }
}