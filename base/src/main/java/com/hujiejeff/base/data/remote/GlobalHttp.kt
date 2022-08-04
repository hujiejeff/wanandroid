package com.hujiejeff.base.data.remote

import com.hujiejeff.base.data.remote.interceptor.NetStateInterceptor
import okhttp3.Interceptor

/*class GlobalHttp private constructor(): HttpAbstract() {
    override fun getInterceptors(): List<Interceptor> = listOf(NetStateInterceptor())
    override val baseUrl: String = "https://www.wanandroid.com"

    companion object {
        @Volatile
        private var instance: GlobalHttp? = null
        fun getInstance(): GlobalHttp {
            return instance ?: synchronized(this) {
                instance ?: GlobalHttp().also { instance = it }
            }
        }
    }

}*/


object GlobalHttp  : HttpAbstract() {
    override fun getInterceptors(): List<Interceptor> = listOf(NetStateInterceptor())
    override val baseUrl: String = "https://www.wanandroid.com"
}