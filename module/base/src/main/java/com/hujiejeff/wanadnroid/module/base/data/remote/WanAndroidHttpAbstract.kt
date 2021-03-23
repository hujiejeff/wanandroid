package com.hujiejeff.wanadnroid.module.base.data.remote


import com.hujiejeff.wanadnroid.module.base.utils.getSuperClassGenericClass
import okhttp3.Interceptor
import okhttp3.Response

abstract class WanAndroidHttpAbstract<T>: HttpAbstract() {
    override val baseUrl: String = "https://www.wanandroid.com"
    val api: T by lazy {
        getRetrofit().create(getSuperClassGenericClass(this))
    }

    private val apiMaps = mutableMapOf<Class<*>, Any>()

    override fun getInterceptors(): List<Interceptor> = listOf()

    class HeaderInterceptor: Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request().newBuilder()
                .addHeader("token", "12345")
                .build()
            return chain.proceed(request)
        }
    }

    fun<T> getApi(clazz: Class<T>): T {
        if (!apiMaps.contains(clazz)) {
            val api = getRetrofit().create(clazz)
            apiMaps[clazz] = api!!
        }
        return apiMaps[clazz] as T
    }
}