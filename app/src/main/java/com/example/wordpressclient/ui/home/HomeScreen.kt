package com.example.wordpressclient.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Notifications
import com.example.wordpressclient.data.sampleArticles
import com.example.wordpressclient.data.Article

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Search + Notification
        item {
            TopBar()
        }

        // Breaking News Section title
        item {
            SectionHeader("Breaking News")
        }

        // Featured Article (lấy bài đầu tiên trong sampleArticles)
        item {
            val featured = sampleArticles.first()
            FeaturedCard(
                title = featured.title,
                author = featured.author,
                date = featured.date,
                views = featured.views,
                imageUrl = featured.imageUrl
            )
        }

        // Recommendation Section title
        item {
            SectionHeader("Recommendation")
        }

        // Suggested articles (các bài còn lại)
        items(sampleArticles.drop(1)) { article ->
            SuggestedItem(
                title = article.title,
                date = article.date,
                views = article.views,
                imageUrl = article.imageUrl
            )
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium.copy(
            fontWeight = FontWeight.Bold
        ),
        fontSize = 22.sp,
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
fun TopBar(
    query: String = "",
    onQueryChanged: (String) -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            singleLine = true,
            maxLines = 1,
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
            placeholder = { Text("Search...", fontSize = 16.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp)
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5),
                disabledContainerColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        BadgedBox(
            badge = {
                Badge { Text("1") }
            }
        ) {
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications"
                )
            }
        }
    }
}
