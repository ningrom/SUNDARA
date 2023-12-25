package com.dicoding.sundara

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.sundara.ui.navigation.NavigationItem
import com.dicoding.sundara.ui.navigation.Screen
import com.dicoding.sundara.ui.screen.article.ArticleScreen
import com.dicoding.sundara.ui.screen.homeScreen.HomeScreen
import com.dicoding.sundara.ui.theme.SundaraTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SundaraApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {

    Scaffold(
        bottomBar = {
            BottomBar()
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ){
            composable(Screen.Home.route){
                HomeScreen()
            }
            composable(Screen.Article.route){
                ArticleScreen()
            }
        }
    }
}

@Composable
private fun BottomBar(
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_article),
                icon = Icons.Default.Menu,
                screen = Screen.Article
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                    )
                },
                label = { Text(item.title)},
                selected = false,
                onClick = {

                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SundaraAppPreview(){
    SundaraTheme {
        SundaraApp()
    }
}