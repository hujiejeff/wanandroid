package com.hujiejeff.wanandroid_compose.ui.home.page

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.hujiejeff.wanandroid_compose.ui.common.Banner
import com.hujiejeff.wanandroid_compose.ui.model.TabItem

@Preview(showBackground = true)
@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Banner()
    }
    Box(modifier = Modifier
        .fillMaxSize()) {


        Text(text = TabItem.Main.title, modifier = Modifier.align(Alignment.Center))
    }
}