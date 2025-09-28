package com.example.wordpressclient.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wordpressclient.ui.discover.Discover

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
            composable(route = BottomNavItem.Home.route) {
                DummyScreen(name = "Home Screen")
            }
            composable(route = BottomNavItem.Discover.route) {
                Discover()
            }
            composable(route = BottomNavItem.Notifications.route) {
                DummyScreen(name = "Notifications Screen")
            }
            composable(route = BottomNavItem.Profile.route) {
                DummyScreen(name = "Profile Screen")
            }
        }
    }
}

@Composable
fun DummyScreen(name: String) {
    androidx.compose.material3.Text(text = name)
}
