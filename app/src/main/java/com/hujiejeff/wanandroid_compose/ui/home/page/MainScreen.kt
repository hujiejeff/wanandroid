package com.hujiejeff.wanandroid_compose.ui.home.page

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.blankj.utilcode.util.ActivityUtils
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hujiejeff.base.utils.TimeUtil
import com.hujiejeff.base.utils.TimeUtil.YYYY_MM_DD_HH_MM
import com.hujiejeff.base.webview.WebViewActivity
import com.hujiejeff.wanandroid_compose.WanAndroidApp
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.BannerBean
import com.hujiejeff.wanandroid_compose.ui.common.BannerImg
import com.hujiejeff.wanandroid_compose.ui.common.Indicators
import com.hujiejeff.wanandroid_compose.ui.home.HomeViewModel
import com.hujiejeff.wanandroid_compose.ui.model.HotTagItem
import com.hujiejeff.wanandroid_compose.ui.model.LoadingState
import com.hujiejeff.wanandroid_compose.utils.ContextHolder
import com.hujiejeff.wanandroid_compose.utils.showToast
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    lazyListState: LazyListState,
    pagerState: PagerState,
    scaffoldState: ScaffoldState
) {
    val homeViewModel = viewModel<HomeViewModel>()
    val mainScreenState by homeViewModel.mainScreenState.collectAsState()
    val cornerShape = rememberCoroutineScope()
    if (mainScreenState.loadingState == LoadingState.UnInit) {
        LaunchedEffect(Unit) {
            launch {
                homeViewModel.loadBanners()
            }
            launch {
                homeViewModel.firstLoadArticles()
            }
        }
    }

    when (mainScreenState.loadingState) {
        LoadingState.LoadSuccess -> {
            showToast("加载成功")
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
        modifier = Modifier.fillMaxSize(),
        state = rememberSwipeRefreshState(mainScreenState.loadingState == LoadingState.RefreshLoading),
        onRefresh = {
            homeViewModel.refreshLoadArticles()
        },
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            ArticleListView(
                header = {
                    TopBanner(bannerBeans = mainScreenState.banners, pagerState = pagerState)
                    TopHotTags()
                    Spacer(Modifier.size(20.dp))
                },
                footer = {

                },
                articles = mainScreenState.articles, lazyListState = lazyListState,
                onLoadMore = { homeViewModel.loadMoreArticles() }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TopBanner(bannerBeans: List<BannerBean>, pagerState: PagerState) {
    val imgUrls = bannerBeans.map { bannerBean ->
        bannerBean.imagePath
    }
    BannerImg(imgUrls = imgUrls, pagerState = pagerState) { i ->
        showToast("点击了${bannerBeans[i].url}")
    }
}

//热门专题
@Composable
fun TopHotTags() {
    val list = listOf(
        HotTagItem.Interview,
        HotTagItem.Share,
        HotTagItem.Optimization,
        HotTagItem.Pub,
        HotTagItem.JetPack,
        HotTagItem.SourceCode,
        HotTagItem.Framework,
        HotTagItem.Kotlin,
    )
    Column() {
        RowTags(list = list.subList(0, 4))
        Spacer(Modifier.size(20.dp))
        RowTags(list = list.subList(4, list.size))
    }
}

@Composable
fun RowTags(list: List<HotTagItem>) {
    Row {
        list.forEach { hotTagItem ->
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(id = hotTagItem.resIconId),
                    contentDescription = null
                )
                Spacer(Modifier.size(5.dp))
                Text(text = hotTagItem.title, style = MaterialTheme.typography.subtitle2)
            }
        }
    }
}


@Composable
fun ArticleListView(
    header: @Composable () -> Unit = {},
    footer: @Composable () -> Unit = {},
    articles: List<ArticleBean>,
    lazyListState: LazyListState,
    onLoadMore: () -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .absolutePadding(bottom = 60.dp)
            .background(color = Color.White),
        state = lazyListState,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            header()
        }
        items(items = articles, key = { article -> article.id }) { article: ArticleBean ->
            ArticleItem(article)
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

@Composable
fun ArticleItem(article: ArticleBean) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable {
                val intent = Intent(ContextHolder.context, WebViewActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    putExtra(WebViewActivity.KEY_WEB_VIEW_PATH, article.link)
                    putExtra(WebViewActivity.KEY_WEB_VIEW_TITLE, article.title)
                }
                ContextHolder.context.startActivity(intent)
            }
            .padding(horizontal = 16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
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
fun Tag(modifier: Modifier = Modifier, tag: String, color: Color = MaterialTheme.colors.primary) {
    Text(
        modifier = modifier
            .border(width = 1.dp, color = color, RoundedCornerShape(5.dp))
            .padding(4.dp), text = tag, color = color, textAlign = TextAlign.Center,
        fontSize = 14.sp
    )
}

