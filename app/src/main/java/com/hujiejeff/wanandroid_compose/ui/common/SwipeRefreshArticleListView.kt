package com.hujiejeff.wanandroid_compose.ui.common

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hujiejeff.base.utils.showToast
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.ui.home.page.ArticleListView
import com.hujiejeff.wanandroid_compose.ui.model.LoadingState

@Composable
fun SwipeRefreshArticleListView(
    modifier: Modifier = Modifier,
    articles: List<ArticleBean>,
    loadingState: LoadingState,
    lazyListState: LazyListState,
    onRefresh: () -> Unit = {},
    onLoadMore: () -> Unit = {},
    onRetry: () -> Unit = {}
) {
    when (loadingState) {
        LoadingState.LoadSuccess -> {
//            showToast("加载成功")
        }
        LoadingState.Error -> {
            showToast("加载失败")
        }
        LoadingState.RefreshSuccess -> {
            showToast("刷新成功")
        }
        else -> {}
    }

    val status = if (articles.isNotEmpty()) {
        LoadStatus.Success
    } else {
        when (loadingState) {
            LoadingState.Error -> LoadStatus.Error
            LoadingState.Loading, LoadingState.UnInit -> LoadStatus.Loading
            LoadingState.Empty -> LoadStatus.Empty
            else -> LoadStatus.Success
        }
    }

    LoadingLayout(modifier = modifier.fillMaxSize(), onRetry = onRetry, loadStatus = status) {
        SwipeRefresh(
            modifier = modifier.fillMaxSize(),
            state = rememberSwipeRefreshState(loadingState == LoadingState.RefreshLoading),
            onRefresh = onRefresh,
        ) {
            ArticleListView(
                articles = articles, lazyListState = lazyListState,
                onLoadMore = onLoadMore
            )
        }
    }
}