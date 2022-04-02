package com.hujiejeff.wanandroid_compose.network.bean

import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean

data class NaviBean(
    val articles: List<ArticleBean>,
    val cid: Int,
    val name: String
)