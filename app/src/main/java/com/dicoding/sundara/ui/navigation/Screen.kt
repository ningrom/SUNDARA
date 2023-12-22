package com.dicoding.sundara.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Article : Screen("article")
}