package com.hujiejeff.base.utils

import android.content.Intent
import com.hujiejeff.base.webview.WebViewActivity

fun startH5(title: String, url: String) {
    val intent = Intent(ContextHolder.context, WebViewActivity::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        putExtra(WebViewActivity.KEY_WEB_VIEW_TITLE, title)
        putExtra(WebViewActivity.KEY_WEB_VIEW_PATH, url)
    }
    ContextHolder.context.startActivity(intent)
}