package com.example.wordpressclient.ui.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordpressclient.R

@Composable
fun Alert() {
    val articles = listOf(
        Article("UX Writing and UX Design: How to Bring Them Together", "Design", "Added 1h ago", R.drawable.ic_launcher_foreground),
        Article("Building a successful Design System", "Design", "Added 3h ago", R.drawable.ic_launcher_foreground),
        Article("Visiting towards the nature all alone", "Travel", "Added 1d ago", R.drawable.ic_launcher_foreground),
        Article("The curious case of Instagram comments", "UX Design", "Added Nov 23, 2022", R.drawable.ic_launcher_foreground)
    )

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(
            text = "Notifications",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn {
            items(articles) { article ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                ) {
                    Image(
                        painter = painterResource(id = article.imageRes),
                        contentDescription = null,
                        modifier = Modifier
                            .size(70.dp)
                            .padding(end = 12.dp)
                    )

                    Column(modifier = Modifier.weight(1f)) {
                        Text(text = article.category, fontSize = 12.sp, color = Color.Gray)
                        Text(
                            text = article.title,
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            maxLines = 2
                        )
                        Text(text = article.time, fontSize = 12.sp, color = Color.LightGray)
                    }

                    Icon(
                        painter = painterResource(id = R.drawable.ic_bookmark_border),
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}

