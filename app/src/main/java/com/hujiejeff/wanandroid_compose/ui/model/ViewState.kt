package com.hujiejeff.wanandroid_compose.ui.model

import androidx.annotation.DrawableRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.outlined.*
import androidx.compose.material.icons.rounded.Place
import androidx.compose.ui.graphics.vector.ImageVector
import com.hujiejeff.wanandroid_compose.R
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.BannerBean

enum class TabItem(val iconSelected: ImageVector, val iconNormal: ImageVector, val title: String) {
    Main(Icons.Filled.Home, Icons.Outlined.Home, "推荐"),
    Tree(Icons.Filled.Build, Icons.Outlined.Build, "项目"),
    Wechat(Icons.Filled.Place, Icons.Outlined.Place, "分类"),
    User(Icons.Filled.Person, Icons.Outlined.Person, "我的"),
}

enum class HotTagItem(val title: String, @DrawableRes val resIconId: Int, val cId: Int) {
    Interview("面试", R.mipmap.icon_iv, 73),
    Share("大厂分享", R.mipmap.icon_big, 510),
    Optimization("性能优化", R.mipmap.icon_op, 78),
    Pub("官方发布", R.mipmap.icon_daily, 269),
    JetPack("Jetpack", R.mipmap.icon_jetpack, 422),
    SourceCode("开源库源码", R.mipmap.icon_open, 460),
    Framework("Framework", R.mipmap.icon_framework, 152),
    Kotlin("Kotlin", R.mipmap.icon_kotlin, 232)
}

enum class LoadingState {
    UnInit,
    IDLE,
    Loading,
    Error,
    Empty,
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

class TopicScreenState(
    val articles: List<ArticleBean>,
    val loadingState: LoadingState = LoadingState.UnInit
)

sealed class Screen(val route: String) {
    private companion object {
        private const val KEY_TOPIC_ID = "key_topic_id"
        private const val ROUTE_LOGIN = "route_Login"
        private const val ROUTE_TOPIC = "route_topic"
        private const val ROUTE_HOME = "route_home"
    }
    object HomeScreen: Screen(route = ROUTE_HOME)
    object LoginScreen: Screen(route = ROUTE_LOGIN)
    object TopicScreen: Screen(route = ROUTE_TOPIC)
}