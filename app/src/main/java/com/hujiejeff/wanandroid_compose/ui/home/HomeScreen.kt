package com.hujiejeff.wanandroid_compose.ui.home

import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.hujiejeff.wanandroid_compose.ui.home.page.MainScreen
import com.hujiejeff.wanandroid_compose.ui.home.page.TreeScreen
import com.hujiejeff.wanandroid_compose.ui.home.page.UserScreen
import com.hujiejeff.wanandroid_compose.ui.home.page.WechatScreen
import com.hujiejeff.wanandroid_compose.ui.home.views.HomeBottomBar
import com.hujiejeff.wanandroid_compose.ui.home.views.HomeTopBar

import com.hujiejeff.wanandroid_compose.ui.model.TabItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen() {
    var selected by remember {
        mutableStateOf(TabItem.Main)
    }
    val lazyListState = rememberLazyListState()
    val pagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()
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
                FloatingActionButton(backgroundColor = MaterialTheme.colors.primary, onClick = {
                    coroutineScope.launch {
                        lazyListState.animateScrollToItem(0)
                    }
                }) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = null, tint = Color.White)
                }
            }
        }
    ) {
        when (selected) {
            TabItem.Main -> {
                MainScreen(lazyListState = lazyListState, pagerState = pagerState)
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