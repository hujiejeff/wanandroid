package com.hujiejeff.wanandroid_compose.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hujiejeff.base.utils.showToast
import com.hujiejeff.wanandroid_compose.ui.model.LoadingState
import kotlinx.coroutines.delay


/**
 * 刷新ListView
 */
@Composable
fun <T> SwipeRefreshListView(
    modifier: Modifier = Modifier,
    dataList: List<T>,
    loadingState: LoadingState,
    lazyListState: LazyListState,
    onRefresh: () -> Unit = {},
    onLoadMore: () -> Unit = {},
    onRetry: () -> Unit = {},
    header: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
    itemContent: @Composable (data: T) -> Unit = {},
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

    val status = if (dataList.isNotEmpty()) {
        LoadStatus.Success
    } else {
        when (loadingState) {
            LoadingState.Error -> LoadStatus.Error
            LoadingState.Loading, LoadingState.UnInit -> LoadStatus.Loading
            LoadingState.Empty -> LoadStatus.Empty
            else -> LoadStatus.Success
        }
    }

    LoadingLayout(modifier = modifier, onRetry = onRetry, loadStatus = status) {
        SwipeRefresh(
            modifier = modifier.fillMaxSize(),
            state = rememberSwipeRefreshState(loadingState == LoadingState.RefreshLoading),
            onRefresh = onRefresh,
        ) {
            ListView(
                itemContent = itemContent,
                header = header,
                footer = footer,
                dataList = dataList, lazyListState = lazyListState,
                onLoadMore = onLoadMore
            )
        }
    }
}


@Composable
fun <T> ListView(
    modifier: Modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White),
    header: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
    dataList: List<T>,
    lazyListState: LazyListState,
    onLoadMore: () -> Unit,
    key: ((item: T) -> Any)? = null,
    itemContent: @Composable (T) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier,
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            header()
        }
        items(items = dataList, key = key) { data: T ->
            itemContent(data)
        }

        item {
            CircularProgressIndicator()
            LaunchedEffect(Unit) {
                delay(1000)
                onLoadMore()
            }
        }
        item {
            footer()
        }
    }
}