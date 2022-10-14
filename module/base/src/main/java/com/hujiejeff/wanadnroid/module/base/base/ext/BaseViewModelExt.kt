package com.hujiejeff.wanadnroid.module.base.base.ext

import android.util.Log
import com.hujiejeff.wanadnroid.module.base.data.remote.bean.BaseBean
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

inline fun <T> CoroutineScope.netLaunch(
    crossinline before: () -> Unit = {},
    crossinline async: suspend () -> BaseBean<T>,
    crossinline fail: (String) -> Unit = {},
    crossinline success: (T) -> Unit
) {
    launch(Dispatchers.Main) {
        before.invoke()
        val data = withContext(Dispatchers.IO) {
            try {
               return@withContext async.invoke() ?: BaseBean(null, 1, "网络错误")
            } catch (e: Exception) {
                Log.d("Http", e.stackTraceToString())
                BaseBean(null, 1, "网络错误")
            }
        }
        if (data.errorCode == 0 && data.data != null) {
            success.invoke(data.data)
        } else {
            fail.invoke(data.errorMsg)
        }
    }
}