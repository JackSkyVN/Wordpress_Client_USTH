package com.example.wordpressclient.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wordpressclient.ui.home.HomeScreen
import androidx.compose.ui.unit.dp
import com.example.wordpressclient.ui.article.ArticleScreen
import com.example.wordpressclient.data.Article

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            // Home
            composable(BottomNavItem.Home.route) {
                HomeScreen(navController) // 👈 truyền navController
            }

            // Các màn khác
            composable(BottomNavItem.Discover.route) { DummyScreen("Discover Screen") }
            composable(BottomNavItem.Notifications.route) { DummyScreen("Notifications Screen") }
            composable(BottomNavItem.Profile.route) { DummyScreen("Profile Screen") }

            // 👇 Route cho ArticleScreen
            composable("article") {
                val article = navController.previousBackStackEntry
                    ?.savedStateHandle
                    ?.get<Article>("article")

                article?.let {
                    ArticleScreen(navController, it)
                }
            }
        }
    }
}

@Composable
fun DummyScreen(name: String) {
    androidx.compose.material3.Text(text = name, modifier = Modifier.padding(24.dp))
}
