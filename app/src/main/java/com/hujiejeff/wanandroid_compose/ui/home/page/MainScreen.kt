package com.hujiejeff.wanandroid_compose.ui.home.page

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hujiejeff.base.utils.TimeUtil
import com.hujiejeff.base.utils.TimeUtil.YYYY_MM_DD_HH_MM
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.BannerBean
import com.hujiejeff.wanandroid_compose.ui.common.BannerImg
import com.hujiejeff.wanandroid_compose.ui.home.HomeViewModel
import com.hujiejeff.wanandroid_compose.utils.showToast
import kotlinx.coroutines.launch

@Composable
fun MainScreen(state: LazyListState) {
    val homeViewModel = viewModel<HomeViewModel>()
    val mainScreenState by homeViewModel.mainScreenState.collectAsState()
    LaunchedEffect(Unit) {
        launch {
            homeViewModel.loadBanners()
        }
        launch {
            homeViewModel.firstLoadArticles(false)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopBanner(bannerBeans = mainScreenState.banners)
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ArticleListView(articles = mainScreenState.articles, state)
        }
    }
}

@Composable
fun TopBanner(bannerBeans: List<BannerBean>) {
    val imgUrls = bannerBeans.map { bannerBean ->
        bannerBean.imagePath
    }
    BannerImg(imgUrls = imgUrls) {i ->
        showToast("点击了${bannerBeans[i].url}")
    }
}


@Composable
fun ArticleListView(articles: List<ArticleBean>, state: LazyListState) {
    LazyColumn(
        modifier = Modifier.absolutePadding(bottom = 60.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        state = state,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = articles, key = { article -> article.id }) { article: ArticleBean ->
            ArticleItem(article)
        }
    }
}

@Composable
fun ArticleItem(article: ArticleBean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.align(Alignment.CenterStart),
                verticalAlignment = Alignment.CenterVertically
            ) {
                article.tags.forEach { tag ->
                    Tag(modifier = Modifier.padding(end = 10.dp), tag = tag.name)
                }
                if (article.author == "") {
                    Text(
                        text = article.shareUser,
                        color = Color.Gray,
                        style = MaterialTheme.typography.subtitle2
                    )
                } else {
                    Text(
                        text = article.author,
                        color = Color.Gray,
                        style = MaterialTheme.typography.subtitle2
                    )
                }

            }
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = TimeUtil.formatTime(article.publishTime, YYYY_MM_DD_HH_MM),
                color = Color.Gray,
                style = MaterialTheme.typography.subtitle2
            )
        }

        Row(modifier = Modifier.padding(top = 10.dp)) {
            Text(text = article.title)
        }

        Box(
            modifier = Modifier
                .padding(top = 10.dp)
                .fillMaxWidth()
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterStart),
                text = "${article.superChapterName}/${article.chapterName}",
                color = Color.Gray,
                style = MaterialTheme.typography.subtitle2
            )
            Icon(
                modifier = Modifier.align(Alignment.CenterEnd),
                imageVector = Icons.Outlined.FavoriteBorder,
                contentDescription = null,
                tint = Color.Red
            )
        }
        Divider(modifier = Modifier.padding(top = 10.dp))
    }
}

@Composable
fun Tag(modifier: Modifier = Modifier, tag: String, color: Color = Color.Red) {
    Text(
        modifier = modifier
            .border(width = 1.dp, color = color, RoundedCornerShape(5.dp))
            .padding(4.dp), text = tag, color = color, textAlign = TextAlign.Center,
        fontSize = 14.sp
    )
}

