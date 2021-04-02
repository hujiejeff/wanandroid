package com.hujiejeff.wanadnroid.module.base.utils

import android.content.Context
import android.os.Bundle
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.launcher.ARouter
import com.hujiejeff.wanadnroid.module.base.R


fun Context.startBySlide(path: String) {
    startBySlide(path, null)
}

fun Context.startByFade(path: String) {
    startByFade(path, null)
}

fun Context.startByFade(path: String, action: (Postcard.() -> Postcard)? = null) {
    startWithTransition(path, R.anim.fade_in, R.anim.fade_out, action)
}

fun Context.startBySlide(path: String, action: (Postcard.() -> Postcard)? = null) {
    startWithTransition(path, R.anim.slide_right_in, R.anim.slide_left_out, action)
}

private fun Context.startWithTransition(
    path: String,
    enterAnim: Int,
    exitAnim: Int,
    action: (Postcard.() -> Postcard)? = null
) {
    ARouter.getInstance()
        .build(path)
        .withTransition(enterAnim, exitAnim)
        .apply {
            action?.invoke(this)
        }.navigation(this)
}

object RouteCenter {
    fun navigation(path: String, bundle: Bundle? = null): Any? {
        val build = ARouter.getInstance().build(path)
        return if (bundle == null) build.navigation() else build.with(bundle).navigation()
    }
}