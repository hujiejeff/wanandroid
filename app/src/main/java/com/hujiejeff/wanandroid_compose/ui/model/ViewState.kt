package com.hujiejeff.wanandroid_compose.ui.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.ui.graphics.vector.ImageVector

enum class TabItem(val icon: ImageVector, val title: String) {
    Main(Icons.Outlined.Favorite, "推荐"),
    Tree(Icons.Outlined.Face, "体系"),
    Wechat(Icons.Outlined.Send, "公众号"),
    User(Icons.Outlined.Settings, "我的"),
}