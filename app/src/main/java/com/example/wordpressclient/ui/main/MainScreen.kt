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
            composable(BottomNavItem.Home.route) { HomeScreen() }
            composable(BottomNavItem.Discover.route) { DummyScreen("Discover Screen") }
            composable(BottomNavItem.Notifications.route) { DummyScreen("Notifications Screen") }
            composable(BottomNavItem.Profile.route) { DummyScreen("Profile Screen") }
        }
    }
}

@Composable
fun DummyScreen(name: String) {
    androidx.compose.material3.Text(text = name, modifier = Modifier.padding(24.dp))
}
