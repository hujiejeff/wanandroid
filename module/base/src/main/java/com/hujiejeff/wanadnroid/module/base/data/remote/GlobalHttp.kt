package com.hujiejeff.wanadnroid.module.base.data.remote

import com.hujiejeff.wanadnroid.module.base.data.remote.interceptor.NetStateInterceptor
import okhttp3.Interceptor

class GlobalHttp private constructor(): HttpAbstract() {
    override fun getInterceptors(): List<Interceptor> = listOf(NetStateInterceptor())
    override val baseUrl: String = "https://www.wanandroid.com"
    private val apiMaps = mutableMapOf<Class<*>, Any>()

    companion object {
        @Volatile
        private var instance: GlobalHttp? = null
        fun getInstance(): GlobalHttp {
            return instance ?: synchronized(this) {
                instance ?: GlobalHttp().also { instance = it }
            }
        }
    }

    fun <T> getApi(clazz: Class<T>): T {
        if (!apiMaps.contains(clazz)) {
            val api = getRetrofit().create(clazz)
            apiMaps[clazz] = api!!
        }
        return apiMaps[clazz] as T
    }

}