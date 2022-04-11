package com.hujiejeff.wanandroid_compose

import com.hujiejeff.base.BaseApplication
import com.hujiejeff.wanandroid_compose.ui.common.CoilImageLoader
import com.hujiejeff.base.utils.ContextHolder

class WanAndroidApp: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        CoilImageLoader.initImageLoader(context = this)
        ContextHolder.context = this
    }
}