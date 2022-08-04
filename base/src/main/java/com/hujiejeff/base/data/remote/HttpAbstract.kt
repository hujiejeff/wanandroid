package com.hujiejeff.base.data.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class HttpAbstract {
    companion object {
        const val TIME_OUT = 30L
    }
    private lateinit var retrofit: Retrofit
    private val apiMaps = mutableMapOf<Class<*>, Any>()

    fun getRetrofit(): Retrofit {
        if (!this::retrofit.isInitialized) {
            val builder = OkHttpClient().newBuilder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(TIME_OUT, TimeUnit.SECONDS)
                .writeTimeout(TIME_OUT, TimeUnit.SECONDS)
            val interceptors = getInterceptors()
            for (interceptor in interceptors) {
                builder.addInterceptor(interceptor)
            }
            val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BASIC }
            builder.addInterceptor(logger)

            retrofit = Retrofit.Builder()
                .client(builder.build())
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }

    inline fun <reified T> getApi(): T{
        val clazz = T::class.java
        return getApi(clazz)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getApi(clazz: Class<T>): T {
        if (!apiMaps.contains(clazz)) {
            val api = getRetrofit().create(clazz)
            apiMaps[clazz] = api!!
        }
        return apiMaps[clazz] as T
    }

    abstract fun getInterceptors(): List<Interceptor>
    abstract val baseUrl: String
}