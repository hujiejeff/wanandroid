package com.hujiejeff.wanandroid_compose.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hujiejeff.wanandroid_compose.ui.model.TabItem

@Composable
fun HomeBottomBar(selected: TabItem, onClickTab: (TabItem) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        BottomNavigationItem(TabItem.Main, selected, onClickTab)
        BottomNavigationItem(TabItem.Tree, selected, onClickTab)
        BottomNavigationItem(TabItem.Wechat, selected, onClickTab)
        BottomNavigationItem(TabItem.User, selected, onClickTab)
    }
}


@Composable
private fun RowScope.BottomNavigationItem(
    item: TabItem,
    selected: TabItem,
    onClickTab: (TabItem) -> Unit = {}
) {
    val selectorColor = Color.Black
    val unSelectorColor = selectorColor.copy(alpha = 0.5f)
    val color = if (item == selected) selectorColor else unSelectorColor
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .weight(1f)
            .background(shape = RectangleShape, color = Color.White)
            .clickable {
                onClickTab(item)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(imageVector = item.icon, contentDescription = null, tint = color)
        Text(text = item.title, color = color)
    }
} 