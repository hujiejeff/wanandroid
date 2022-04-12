package com.hujiejeff.wanandroid_compose.ui.home.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.pagerTabIndicatorOffset
import com.google.accompanist.pager.rememberPagerState
import com.hujiejeff.wanandroid_compose.ui.home.HomeViewModel
import com.hujiejeff.wanandroid_compose.ui.model.TabItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Preview(showBackground = true)
@Composable
fun TreeScreen() {
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
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = projectTrees[page].name, modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}