package com.hujiejeff.wanandroid_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hujiejeff.wanandroid_compose.ui.home.HomeScreen
import com.hujiejeff.wanandroid_compose.ui.model.Screen
import com.hujiejeff.wanandroid_compose.ui.model.TabItem
import com.hujiejeff.wanandroid_compose.ui.theme.WanandroidcomposeTheme
import com.hujiejeff.wanandroid_compose.ui.topic.TopicScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WanandroidcomposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavigationView()
                }
            }
        }
    }

    @Composable
    fun NavigationView() {
        val navController = rememberNavController()
        var HomeSelected by remember {
            mutableStateOf(TabItem.Main)
        }
        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
            composable(Screen.HomeScreen.route) {
                HomeScreen(navController = navController)
            }
            composable(
                Screen.TopicScreen.route + "/{title}/{cId}",
                arguments = listOf(
                    navArgument("cId") { type = NavType.IntType },
                    navArgument("title") { type = NavType.StringType })
            ) { entry ->
                TopicScreen(navController, entry.arguments?.getInt("cId")!!, entry.arguments?.getString("title")!!)
            }
        }

    }
}