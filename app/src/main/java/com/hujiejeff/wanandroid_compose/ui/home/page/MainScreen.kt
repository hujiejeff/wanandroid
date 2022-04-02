package com.hujiejeff.wanandroid_compose.ui.home.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.ui.common.BannerImg
import com.hujiejeff.wanandroid_compose.ui.home.HomeViewModel
import com.hujiejeff.wanandroid_compose.ui.model.TabItem
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    val homeViewModel = HomeViewModel()
    val mainScreenState by homeViewModel.mainScreenState.collectAsState()
    LaunchedEffect(mainScreenState) {
        launch {
            homeViewModel.loadMoreArticles()
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        BannerImg()
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ArticleListView(articles = mainScreenState.articles)
        }
    }
}


@Composable
fun ArticleListView(articles: List<ArticleBean>) {
    Column() {
        articles.forEach { bean ->
            Card(
                modifier = Modifier
                    .padding(all = 10.dp)
                    .height(60.dp)
                    .fillMaxWidth(),
                elevation = 10.dp
            ) {
                Text(bean.title)
            }
        }
    }
}