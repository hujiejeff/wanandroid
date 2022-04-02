package com.hujiejeff.wanandroid_compose.utils

import android.widget.Toast

fun showToast(msg: Any?) {
    Toast.makeText(ContextHolder.context, msg.toString(), Toast.LENGTH_SHORT).show()
}