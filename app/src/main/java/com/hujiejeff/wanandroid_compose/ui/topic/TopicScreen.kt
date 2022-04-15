package com.hujiejeff.wanandroid_compose.ui.topic

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.ui.common.SwipeRefreshListView
import com.hujiejeff.wanandroid_compose.ui.home.page.ArticleItem
import com.hujiejeff.wanandroid_compose.ui.model.LoadingState
import kotlinx.coroutines.launch


@OptIn(ExperimentalPagerApi::class)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopicScreen(navController: NavController, cId: Int, title: String) {
    val topicViewModel = viewModel<TopicViewModel>()
    topicViewModel.cId = cId
    val lazyListState = rememberLazyListState()
    val topicScreenState by topicViewModel.topicScreenState.collectAsState()
    if (topicScreenState.loadingState == LoadingState.UnInit) {
        LaunchedEffect(Unit) {
            launch {
                topicViewModel.firstLoadArticles()
            }
        }
    }
    Scaffold(topBar = {
        TitleBar(title = title, navController = navController)
    }) {
        SwipeRefreshListView(
            modifier = Modifier.fillMaxSize(),
            dataList = topicScreenState.articles,
            loadingState = topicScreenState.loadingState,
            lazyListState = lazyListState,
            onRefresh = topicViewModel::refreshLoadArticles,
            onLoadMore = topicViewModel::loadMoreArticles,
            onRetry = topicViewModel::firstLoadArticles
        ) { data: ArticleBean ->  
            ArticleItem(article = data)
        }
    }
}

@Composable
fun TitleBar(title: String, navController: NavController) {
    TopAppBar(
        modifier = Modifier.background(MaterialTheme.colors.primary),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.primary)
            ) {
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable {
                            navController.navigateUp()
                        },
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = title,
                    textAlign = TextAlign.Center
                )
            }
        }
    )
}