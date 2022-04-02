package com.hujiejeff.wanandroid_compose

import android.app.Application
import com.hujiejeff.base.BaseApplication
import com.hujiejeff.wanandroid_compose.ui.common.CoilImageLoader
import com.hujiejeff.wanandroid_compose.utils.ContextHolder

class WanAndroidApp: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        CoilImageLoader.initImageLoader(context = this)
        ContextHolder.context = this
    }
}