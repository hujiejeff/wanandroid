package com.hujiejeff.wanandroid_compose.ui.home.page

import android.annotation.SuppressLint
import android.content.Intent
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.hujiejeff.base.utils.TimeUtil
import com.hujiejeff.base.utils.TimeUtil.YYYY_MM_DD_HH_MM
import com.hujiejeff.base.webview.WebViewActivity
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.network.bean.BannerBean
import com.hujiejeff.wanandroid_compose.ui.common.BannerImg
import com.hujiejeff.wanandroid_compose.ui.home.HomeViewModel
import com.hujiejeff.wanandroid_compose.ui.model.HotTagItem
import com.hujiejeff.wanandroid_compose.ui.model.LoadingState
import com.hujiejeff.base.utils.ContextHolder
import com.hujiejeff.base.utils.showToast
import com.hujiejeff.base.utils.startH5
import com.hujiejeff.wanandroid_compose.ui.model.Screen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun MainScreen(
    navController: NavController,
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
                homeViewModel.startupLoadData()
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
        modifier = Modifier.fillMaxSize().padding(bottom = 60.dp),
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
                    TopHotTags { hotTagItem -> navController.navigate("${Screen.TopicScreen.route}/${hotTagItem.title}/${hotTagItem.cId}") }
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
        startH5(title = bannerBeans[i].title, url = bannerBeans[i].url)
    }
}

//热门专题
@Composable
fun TopHotTags(onClick: (HotTagItem) -> Unit) {
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
        RowTags(list = list.subList(0, 4), onClick)
        Spacer(Modifier.size(20.dp))
        RowTags(list = list.subList(4, list.size), onClick)
    }
}

@Composable
fun RowTags(list: List<HotTagItem>, onClick: (HotTagItem) -> Unit) {
    Row {
        list.forEach { hotTagItem ->
            Column(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onClick(hotTagItem)
                    },
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
            .fillMaxWidth()
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
                startH5(title = article.title, url = article.link)
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

