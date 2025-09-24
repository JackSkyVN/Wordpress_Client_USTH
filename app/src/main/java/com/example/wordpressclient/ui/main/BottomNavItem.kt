package com.example.wordpressclient.ui.main
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val label: String, val icon: ImageVector) {
    object Home : BottomNavItem("home", "Home", androidx.compose.material.icons.Icons.Default.Home)
    object Discover : BottomNavItem("discover", "Discover", androidx.compose.material.icons.Icons.Default.Search)
    object Notifications : BottomNavItem("notifications", "Alerts", androidx.compose.material.icons.Icons.Default.Notifications)
    object Profile : BottomNavItem("profile", "Profile", androidx.compose.material.icons.Icons.Default.Person)
}
