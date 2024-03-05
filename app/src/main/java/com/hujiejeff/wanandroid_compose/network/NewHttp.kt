package com.hujiejeff.wanandroid_compose.network

import com.hujiejeff.library.base.network.interceptor.NetStateInterceptor
import com.szpgm.commonlib.network.HttpAbstract
import okhttp3.Interceptor
import retrofit2.CallAdapter

object NewHttp: HttpAbstract() {
    override val baseUrl: String = "https://www.wanandroid.com"
    override val useStandardResponseBean: Boolean = false
    override fun getInterceptors(): List<Interceptor> {
        return listOf(NetStateInterceptor())
    }
}