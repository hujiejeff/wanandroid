package com.hujiejeff.wanandroid_compose.ui.home.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hujiejeff.wanandroid_compose.ui.model.TabItem

@Composable
fun HomeTopBar(tabItem: TabItem) {
    var search by remember {
        mutableStateOf("")
    }
    TopAppBar(
        modifier = Modifier.background(MaterialTheme.colors.primary),
        navigationIcon = {
            Text(modifier = Modifier.padding(start = 10.dp), text = tabItem.title)
        },
        title = {
/*            TextField(
                modifier = Modifier.background(MaterialTheme.colors.primary),
                value = search,
                onValueChange = { search = it },
                singleLine = true
            )*/
        },
        actions = {
            Icon(
                modifier = Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .size(30.dp),
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                tint = Color.White
            )
            Icon(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .size(30.dp),
                imageVector = Icons.Filled.Settings,
                contentDescription = null,
                tint = Color.White
            )
        }
    )
}

@Preview
@Composable
fun textPreview() {
    HomeTopBar(tabItem = TabItem.Main)
}