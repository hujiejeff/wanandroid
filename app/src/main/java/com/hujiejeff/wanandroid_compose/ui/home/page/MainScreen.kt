package com.hujiejeff.wanandroid_compose.ui.home.page

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hujiejeff.base.utils.TimeUtil
import com.hujiejeff.base.utils.TimeUtil.YYYY_MM_DD_HH_MM
import com.hujiejeff.wanandroid_compose.network.bean.ArticleBean
import com.hujiejeff.wanandroid_compose.ui.common.BannerImg
import com.hujiejeff.wanandroid_compose.ui.home.HomeViewModel
import com.hujiejeff.wanandroid_compose.ui.model.TabItem
import kotlinx.coroutines.launch

@Composable
fun MainScreen(state: ScrollState) {
    val homeViewModel = viewModel<HomeViewModel>()
    val mainScreenState by homeViewModel.mainScreenState.collectAsState()
    LaunchedEffect(Unit) {
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
            ArticleListView(articles = mainScreenState.articles, state)
        }
    }
}


@Composable
fun ArticleListView(articles: List<ArticleBean>, state: ScrollState) {
    Column(
        modifier = Modifier
            .verticalScroll(state = state)
            .absolutePadding(bottom = 60.dp)
    ) {
        articles.forEach { article ->
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
            .padding(10.dp)
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
                    Text(text = article.shareUser,color = Color.Gray,  fontSize = 14.sp)
                } else {
                    Text(text = article.author, color = Color.Gray,  fontSize = 14.sp)
                }

            }
            Text(
                modifier = Modifier.align(Alignment.CenterEnd),
                text = TimeUtil.formatTime(article.publishTime, YYYY_MM_DD_HH_MM),
                color = Color.Gray,
                fontSize = 14.sp
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
                fontSize = 14.sp
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

