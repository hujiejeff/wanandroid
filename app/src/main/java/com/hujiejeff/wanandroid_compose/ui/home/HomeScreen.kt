package com.hujiejeff.wanandroid_compose.ui.home

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import com.hujiejeff.wanandroid_compose.ui.home.page.MainScreen
import com.hujiejeff.wanandroid_compose.ui.home.page.TreeScreen
import com.hujiejeff.wanandroid_compose.ui.home.page.UserScreen
import com.hujiejeff.wanandroid_compose.ui.home.page.WechatScreen
import com.hujiejeff.wanandroid_compose.ui.home.views.HomeBottomBar
import com.hujiejeff.wanandroid_compose.ui.home.views.HomeTopBar

import com.hujiejeff.wanandroid_compose.ui.model.TabItem

@Composable
fun HomeScreen() {
    var selected by remember {
        mutableStateOf(TabItem.Main)
    }
    Scaffold(
        topBar = {
            HomeTopBar(selected)
        },
        bottomBar = {
            HomeBottomBar(selected) {
                selected = it
            }
        },
        drawerContent = {},
        floatingActionButton = {
            if (selected == TabItem.Main) {
                FloatingActionButton(onClick = {  }) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null)
                }
            }
        }
    ) {
        when (selected) {
            TabItem.Main -> {
                MainScreen()
            }
            TabItem.Tree -> {
                TreeScreen()
            }
            TabItem.Wechat -> {
                WechatScreen()
            }
            TabItem.User -> {
                UserScreen()
            }
        }
    }
}