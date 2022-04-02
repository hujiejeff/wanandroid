package com.hujiejeff.wanandroid_compose.ui.home.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.hujiejeff.wanandroid_compose.ui.model.TabItem

@Preview(showBackground =true)
@Composable
fun UserScreen() {
    Box(modifier = Modifier
        .fillMaxSize()) {
        Text(text = TabItem.User.title, modifier = Modifier.align(Alignment.Center))
    }
}