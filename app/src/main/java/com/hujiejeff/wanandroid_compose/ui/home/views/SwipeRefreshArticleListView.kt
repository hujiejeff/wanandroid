package com.hujiejeff.wanandroid_compose.ui.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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

    SwipeRefresh(
        modifier = modifier.fillMaxSize(),
        state = rememberSwipeRefreshState(loadingState == LoadingState.RefreshLoading),
        onRefresh = onRefresh,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            if (articles.isNotEmpty()) {
                ArticleListView(
                    articles = articles, lazyListState = lazyListState,
                    onLoadMore = onLoadMore
                )
            } else {
                when (loadingState) {
                    LoadingState.Error -> {
                        Text(modifier = Modifier.align(Alignment.Center).clickable {
                            onRetry()
                        }, text = "网络出错，点击重试")
                    }

                    LoadingState.Loading, LoadingState.UnInit -> {
                        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                    }

                    LoadingState.Empty -> {
                        Text(modifier = Modifier.align(Alignment.Center).clickable {
                            onRetry()
                        }, text = "暂时没有数据，点击重试")
                    }

                    else -> {}
                }
            }

        }
    }
}