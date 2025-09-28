package com.example.wordpressclient.ui.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home : BottomNavItem("home", Icons.Filled.Home, "Home")
    object Discover : BottomNavItem("discover", Icons.Filled.Search, "Discover")
    object Notifications : BottomNavItem("notifications", Icons.Filled.Notifications, "Alerts")
    object Profile : BottomNavItem("profile", Icons.Filled.Person, "Profile")
}
