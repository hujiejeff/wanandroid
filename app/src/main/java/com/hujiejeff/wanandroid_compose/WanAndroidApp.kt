package com.hujiejeff.wanandroid_compose

import com.hujiejeff.base.BaseApplication
import com.hujiejeff.base.utils.ContextHolder
import com.hujiejeff.wanandroid_compose.ui.common.CoilImageLoader

class WanAndroidApp: BaseApplication() {
    override fun onCreate() {
        super.onCreate()
        CoilImageLoader.initImageLoader(context = this)
        ContextHolder.context = this
    }
}