package com.example.wordpressclient.ui.main

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wordpressclient.ui.home.HomeScreen
import com.example.wordpressclient.ui.notification.Alert
import com.example.wordpressclient.ui.profile.EditProfileScreen
import com.example.wordpressclient.ui.profile.MyProfileScreen

@Composable
fun MainScreen(onLogoutClick: () -> Unit) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController = navController) } // Pass navController here
    ) { innerPadding ->
        NavHost(navController = navController,
            startDestination = BottomNavItem.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavItem.Home.route) {
                HomeScreen(navController)
            }
            composable(BottomNavItem.Discover.route) {DummyScreen("Discover Screen") }
            composable(BottomNavItem.Notifications.route) {Alert()}
            composable(route = BottomNavItem.Profile.route) {
                MyProfileScreen(
                    onEditClick = {
                        navController.navigate("edit_profile")
                    }
                )
            }
            composable("edit_profile") {
                EditProfileScreen(
                    onBack = { navController.popBackStack() },
                    onSave = { navController.popBackStack() }

                )
            }
        }
    }
}
@Composable
fun DummyScreen(name: String) {
    androidx.compose.material3.Text(text = name, modifier = Modifier.padding(24.dp))
}

