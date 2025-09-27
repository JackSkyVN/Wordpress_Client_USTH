package com.example.wordpressclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.wordpressclient.navigation.Screen
import com.example.wordpressclient.ui.auth.LoginScreen
import com.example.wordpressclient.ui.auth.RegisterScreen
import com.example.wordpressclient.ui.main.MainScreen
import com.example.wordpressclient.ui.theme.WordpressClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordpressClientTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // in-memory "database" of registered accounts for demo: pair(phone -> password)
    val registered = remember { mutableStateListOf<Pair<String, String>>() }
    // seed one demo account (optional)
    if (registered.isEmpty()) {
        registered.add("555" to "555") // you can remove or change
    }

    NavHost(navController = navController, startDestination = Screen.Login.route) {

        composable(Screen.Login.route) {
            LoginScreen(
                onLoginClick = { phone, password ->
                    // 1) Hardcoded super-account
                    if (phone == "123" && password == "123") {
                        navController.navigate(Screen.Main.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                        return@LoginScreen
                    }
                    // 2) Or match registered in-memory list
                    val matched = registered.any { it.first == phone && it.second == password }
                    if (matched) {
                        navController.navigate(Screen.Main.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                        }
                    } else {
                        // if not matched, we keep on the login screen and the LoginScreen itself will show error
                        // we could also pass back an error callback but simpler: LoginScreen handles error states locally
                    }
                },
                onSignUpClick = { navController.navigate(Screen.Register.route) },
                onGoogleClick = { /* TODO */ },
                onAppleClick = { /* TODO */ },
                onGuestClick = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                },
                registeredProvider = { registered.toList() } // provide current registered list for feedback (optional)
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                onSignInClick = { navController.popBackStack() },
                onSignUpClick = { phone, email, password ->
                    // validation already done in RegisterScreen, so here just add account and navigate
                    registered.add(phone to password)
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Main.route) {
            MainScreen(
                onLogoutClick = {
                    navController.navigate(Screen.Login.route) {
                        popUpTo(Screen.Login.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
