package com.hujiejeff.wanandroid_compose.ui.home.views

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
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
    val selectorColor = MaterialTheme.colors.primary
    val unSelectorColor = Color.Black
    val color = if (item == selected) selectorColor else unSelectorColor
    val icon = if (item == selected) item.iconSelected else item.iconNormal
    val scale: Float by animateFloatAsState(if (item == selected) 1.2f else 1f)
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .clip(shape = MaterialTheme.shapes.large)
            .weight(1f)
            .clickable {
                onClickTab(item)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(modifier = Modifier
            .wrapContentWidth()
            .scale(scale = scale), imageVector = icon, contentDescription = null, tint = color)
        Spacer(modifier = Modifier.size(5.dp))
        Text(text = item.title, color = color)
    }
} 