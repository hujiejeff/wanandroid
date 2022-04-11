package com.hujiejeff.base.data.remote.interceptor

import android.accounts.NetworkErrorException
import com.blankj.utilcode.util.NetworkUtils
import com.google.gson.Gson
import com.hujiejeff.base.data.remote.bean.BaseBean
import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody

class NetStateInterceptor : Interceptor {
    private val gson = Gson()
    override fun intercept(chain: Interceptor.Chain): Response {
        try {
            val request = chain.request()
            //无网络
            if (!NetworkUtils.isConnected()) {
                throw NetworkErrorException("no network")
            }
            val response = chain.proceed(request)
            if (!response.isSuccessful) {
                throw RuntimeException("server: ${response.message}")
            }
            return response
        } catch (e: Exception) {
            val errorBean = BaseBean(null, -1, "网络不稳定")
            val errorBody = gson.toJson(errorBean).toResponseBody()
            return Response.Builder()
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .message(errorBean.errorMsg)
                .body(errorBody)
                .code(200)
                .build()
        }
    }
}