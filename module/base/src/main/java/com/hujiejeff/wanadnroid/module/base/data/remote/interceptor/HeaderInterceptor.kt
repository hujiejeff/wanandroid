package com.hujiejeff.wanadnroid.module.base.data.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor : Interceptor {

    private val mHeaders = mutableMapOf<String, String>()

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder().apply {
            for ((key, value) in mHeaders) {
                addHeader(key, value)
            }
        }.build()
        return chain.proceed(request)
    }

    fun addHeader(name: String, value: String) {
        mHeaders[name] = value
    }

    fun addHeads(headerMap: Map<String, String>) {
        mHeaders.putAll(headerMap)
    }
}