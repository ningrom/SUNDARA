package com.dicoding.sundara.ui

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
import com.dicoding.sundara.R
import com.dicoding.sundara.ui.navigation.NavigationItem
import com.dicoding.sundara.ui.navigation.Screen

@Composable
fun SundaraApp(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        bottomBar = {
            BottomBar()
        },
        modifier = modifier
    ) { innerPadding ->

    }
}


@Composable
private fun BottomBar(
    modifier: Modifier = Modifier
) {
    NavigationBar(modifier = Modifier) {
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

