package com.example.wordpressclient.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.ui.unit.dp
import com.example.wordpressclient.ui.home.HomeScreen
import androidx.navigation.compose.rememberNavController
import com.example.wordpressclient.ui.article.ArticleScreen
import com.example.wordpressclient.data.Article

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.padding(16.dp)
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") {
                HomeScreen(navController)
            }

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
