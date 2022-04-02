package com.hujiejeff.wanandroid_compose.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.BannerBean

enum class TabItem(val icon: ImageVector, val title: String) {
    Main(Icons.Outlined.Favorite, "推荐"),
    Tree(Icons.Outlined.Face, "体系"),
    Wechat(Icons.Outlined.Send, "公众号"),
    User(Icons.Outlined.Settings, "我的"),
}

enum class LoadingState {
    Loading,
    Error,
    Success,
    RefreshLoading,
    LoadingMore
}


class MainScreenState(
    val banners: List<BannerBean>,
    val articles: List<ArticleBean>,
    val loadingState: LoadingState
)