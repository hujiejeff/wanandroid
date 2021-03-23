package com.hujiejeff.wanadnroid.module.base.data.remote.interceptor

import com.google.gson.Gson
import com.hujiejeff.wanadnroid.module.base.data.remote.bean.BaseBean
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody

class NetStateInterceptor : Interceptor {
    private val gson = Gson()
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        var response = chain.proceed(request)
        val errorBean: BaseBean<*>
        if (response.code != 200) {
            errorBean = BaseBean(null, -1, "网络出错")
            val errorBody = gson.toJson(errorBean).toResponseBody()
            response = response.newBuilder().body(errorBody).build()
        } else {
            if (response.body == null) {
                errorBean = BaseBean(null, -1, "系统繁忙")
                val errorBody = gson.toJson(errorBean).toResponseBody()
                response = response.newBuilder().body(errorBody).build()
            }
        }
        return response
    }
}