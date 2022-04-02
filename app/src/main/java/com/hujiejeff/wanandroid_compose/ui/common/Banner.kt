package com.hujiejeff.wanandroid_compose.ui.common

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.RangeSlider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager


@Composable
fun BannerImg() {
    val imgUrls =
        listOf(
            "https://www.wanandroid.com/blogimgs/42da12d8-de56-4439-b40c-eab66c227a4b.png",
            "https://www.wanandroid.com/blogimgs/62c1bd68-b5f3-4a3c-a649-7ca8c7dfabe6.png",
            "https://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png",
        )
    Banner(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), imgUrls.size
    ) {
        NewWorkImage(
            modifier = Modifier.fillMaxSize(),
            imgUrls[it]
        )
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Banner(modifier: Modifier = Modifier, count: Int, content: @Composable (Int) -> Unit) {
    Box {
        HorizontalPager(count = count, modifier = modifier) { page ->
            content(page)
        }
        Indicators(modifier = Modifier.align(Alignment.BottomCenter), count = count)
    }
}

@Composable
fun Indicators(modifier: Modifier = Modifier, count: Int = 1) {
    Row(modifier = modifier) {
        repeat(count) {
            CircleIndicator(modifier = Modifier.padding(start = 2.dp, end = 2.dp))
        }
    }
}

@Composable
fun CircleIndicator(modifier: Modifier = Modifier) {
    Canvas(modifier = modifier.size(5.dp)) {
        val w = size.width
        val h = size.height
        drawCircle(
            color = Color.Gray,
            center = Offset(x = w / 2, y = h / w),
            radius = size.minDimension / 2
        )
    }
}