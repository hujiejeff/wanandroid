package com.hujiejeff.wanandroid_compose.network

import com.hujiejeff.base.data.remote.bean.BaseBean
import com.hujiejeff.library.base.network.entity.ResponseBean
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.PageBean
import retrofit2.http.GET
import retrofit2.http.Path

interface NewApi {
    @GET("/article/list/{page}/json")
    suspend fun getMainArticles(@Path("page") page: Int): ResponseBean<BaseBean<PageBean<ArticleBean>>>
}