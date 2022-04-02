package com.hujiejeff.wanandroid_compose

import android.app.Application
import com.hujiejeff.wanandroid_compose.ui.common.CoilImageLoader

class WanAndroidApp: Application() {
    override fun onCreate() {
        super.onCreate()
        CoilImageLoader.initImageLoader(context = this)
    }
}