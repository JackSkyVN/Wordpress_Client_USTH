package com.example.wordpressclient
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.wordpressclient.ui.main.MainScreen
import com.example.wordpressclient.ui.theme.WordpressClientTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WordpressClientTheme{
                MainScreen()
            }
        }
    }
}
