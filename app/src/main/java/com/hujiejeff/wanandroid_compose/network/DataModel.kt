package com.hujiejeff.wanandroid_compose.network

import android.util.Log
import com.hujiejeff.base.data.remote.GlobalHttp
import com.hujiejeff.base.data.remote.bean.BaseBean
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.PageBean

object DataModel: WanAndroidApi by GlobalHttp.getApi() {
    val newApi = NewHttp.getApi(NewApi::class.java)
    override suspend fun getMainArticles(page: Int): BaseBean<PageBean<ArticleBean>> {
        val responseBean = newApi.getMainArticles(page)
        Log.d("hujie", "getMainArticles: " + responseBean.errorCode)
        return responseBean.response!!
    }
}
