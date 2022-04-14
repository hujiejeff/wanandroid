package com.hujiejeff.wanandroid_compose.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.hujiejeff.wanandroid_compose.ui.home.page.MainScreen
import com.hujiejeff.wanandroid_compose.ui.home.project.ProjectScreen
import com.hujiejeff.wanandroid_compose.ui.home.page.UserScreen
import com.hujiejeff.wanandroid_compose.ui.home.page.WechatScreen
import com.hujiejeff.wanandroid_compose.ui.home.views.HomeBottomBar
import com.hujiejeff.wanandroid_compose.ui.home.views.HomeTopBar

import com.hujiejeff.wanandroid_compose.ui.model.TabItem
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(navController: NavController) {
    var selected by remember {
        mutableStateOf(TabItem.Main)
    }
    val lazyListState = rememberLazyListState()
    val pagerState = rememberPagerState(0)
    val coroutineScope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scaffoldState = rememberScaffoldState(drawerState = drawerState)
    Scaffold(
        scaffoldState = scaffoldState,
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
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowUp,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            }
        },
        snackbarHost = {
            SnackbarHost(it) { data ->
                Snackbar(
                    modifier = Modifier.imePadding(),
                    backgroundColor = MaterialTheme.colors.primary,
                    elevation = 0.dp,
                    snackbarData = data,
                )
            }
        }
    ) {
        Box(modifier = Modifier.absolutePadding(bottom = 60.dp)) {
            when (selected) {
                TabItem.Main -> {
                    MainScreen(
                        navController = navController,
                        lazyListState = lazyListState,
                        pagerState = pagerState,
                        scaffoldState = scaffoldState,
                    )
                }
                TabItem.Tree -> {
                    ProjectScreen()
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
}