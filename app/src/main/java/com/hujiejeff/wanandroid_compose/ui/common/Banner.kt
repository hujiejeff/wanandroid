package com.hujiejeff.wanandroid_compose.ui.common

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*


//demo
@OptIn(ExperimentalPagerApi::class)
@Composable
fun BannerImg(pagerState: PagerState, imgUrls: List<String>, onclick: (Int) -> Unit = {}) {
/*    val imgUrls =
        listOf(
            "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
            "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
            "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
        )*/
    Banner(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), pagerState = pagerState, count = imgUrls.size
    ) { i ->
        Card(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxSize(),
            elevation = 5.dp
        ) {
            NewWorkImage(
                modifier = Modifier
                    .clickable {
                        onclick(i)
                    },
                imgUrls[i]
            )
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(
    modifier: Modifier = Modifier,
    pagerState: PagerState,
    count: Int,
    content: @Composable (Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val timer = Timer()
    val timerTask = object : TimerTask() {
        override fun run() {
            scope.launch(Dispatchers.Main) {
                val nextPageIndex = (pagerState.currentPage + 1) % count
                pagerState.animateScrollToPage(nextPageIndex)
            }
        }
    }
    Box {
        HorizontalPager(
            count = count,
            modifier = modifier,
            state = pagerState
        ) { page ->
            content(page)
        }

        Indicators(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 20.dp),
            count = count,
            pagerState.currentPage
        )
    }
}

@Composable
fun Indicators(modifier: Modifier = Modifier, count: Int = 1, currentIndex: Int) {
    Row(modifier = modifier) {
        repeat(count) { i ->
            CircleIndicator(
                modifier = Modifier.padding(start = 2.dp, end = 2.dp),
                i == currentIndex
            )
        }
    }
}

@Composable
fun CircleIndicator(modifier: Modifier = Modifier, isCurrentIndex: Boolean) {
    val currentColor = Color.Gray
    val normalColor = Color.White
    val color = if (isCurrentIndex) currentColor else normalColor
    Canvas(modifier = modifier.size(5.dp)) {
        val w = size.width
        val h = size.height
        drawCircle(
            color = color,
            center = Offset(x = w / 2, y = h / w),
            radius = size.minDimension / 2
        )
    }
}