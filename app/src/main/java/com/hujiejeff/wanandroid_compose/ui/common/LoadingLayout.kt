package com.hujiejeff.wanandroid_compose.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


@Composable
fun LoadingLayout(
    modifier: Modifier = Modifier,
    loadStatus: LoadStatus = LoadStatus.Loading,
    onRetry: () -> (Unit) = {},
    content: @Composable BoxScope.() -> (Unit),
) {
    Box(modifier = modifier) {
        when (loadStatus) {
            LoadStatus.Error -> {
                Text(modifier = Modifier.align(Alignment.Center).clickable {
                    onRetry()
                }, text = "网络出错，点击重试")
            }

            LoadStatus.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            LoadStatus.Empty -> {
                Text(modifier = Modifier.align(Alignment.Center).clickable {
                    onRetry()
                }, text = "暂时没有数据，点击重试")
            }

            LoadStatus.Success -> {
                content()
            }
        }
    }
}

enum class LoadStatus {
    Loading,
    Empty,
    Error,
    Success
}

