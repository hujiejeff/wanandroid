package com.hujiejeff.wanandroid_compose.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.BannerBean

enum class TabItem(val iconSelected: ImageVector, val iconNormal: ImageVector, val title: String) {
    Main(Icons.Filled.Home, Icons.Outlined.Home, "推荐"),
    Tree(Icons.Filled.Build, Icons.Outlined.Build, "项目"),
    Wechat(Icons.Filled.Place, Icons.Outlined.Place, "分类"),
    User(Icons.Filled.Person, Icons.Outlined.Person, "我的"),
}

enum class LoadingState {
    UnInit,
    IDLE,
    Loading,
    Error,
    LoadSuccess,
    RefreshLoading,
    RefreshSuccess,
    LoadingMore
}


class MainScreenState(
    val banners: List<BannerBean>,
    val articles: List<ArticleBean>,
    val loadingState: LoadingState = LoadingState.UnInit
)