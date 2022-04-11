package com.hujiejeff.base.utils

import android.widget.Toast

fun showToast(msg: Any?) {
    Toast.makeText(ContextHolder.context, msg.toString(), Toast.LENGTH_SHORT).show()
}