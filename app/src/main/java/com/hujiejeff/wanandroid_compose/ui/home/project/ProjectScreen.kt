package com.hujiejeff.wanandroid_compose.ui.home.project

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.*
import com.hujiejeff.wanandroid_compose.network.bean.TreeBean
import com.hujiejeff.wanandroid_compose.ui.home.HomeViewModel
import com.hujiejeff.wanandroid_compose.ui.home.views.SwipeRefreshArticleListView
import com.hujiejeff.wanandroid_compose.ui.model.ArticleListState
import com.hujiejeff.wanandroid_compose.ui.model.LoadingState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun ProjectScreen() {
    val viewModel = viewModel<HomeViewModel>()
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val projectTrees = viewModel.projectTrees
    Column {
        ScrollableTabRow(
            edgePadding = 0.dp,
            selectedTabIndex = pagerState.currentPage,
            indicator = { tabPositions ->
                TabRowDefaults.Indicator(
                    Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
                )
            }
        ) {
            // Add tabs for all of our pages
            projectTrees.forEachIndexed { index, projectTree ->
                Tab(
                    text = { Text(projectTree.name) },
                    selected = pagerState.currentPage == index,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                )
            }
        }

        HorizontalPager(
            count = projectTrees.size,
            state = pagerState,
        ) { page ->
            ProjectPage(treeBean = projectTrees[page], state = pagerState, index = page)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ProjectPage(treeBean: TreeBean, state: PagerState, index: Int) {
    val projectViewModel = viewModel<ProjectViewModel>(key = treeBean.name)
    val articleListState by projectViewModel.articlesState.collectAsState()
    val lazyListState = rememberLazyListState()
    if (articleListState.loadingState == LoadingState.UnInit && state.currentPage == index) {
        LaunchedEffect(Unit) {
            launch {
                projectViewModel.firstLoadArticles(treeBean = treeBean)
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        SwipeRefreshArticleListView(
            articles = articleListState.articles,
            loadingState = articleListState.loadingState,
            lazyListState = lazyListState,
            onRefresh = projectViewModel::refreshLoadArticles,
            onLoadMore = projectViewModel::loadMoreArticles,
            onRetry = { projectViewModel.firstLoadArticles(treeBean = treeBean) }
        )
    }
}