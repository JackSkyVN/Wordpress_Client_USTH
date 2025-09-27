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
import androidx.navigation.NavController
import com.example.wordpressclient.data.sampleArticles
import com.example.wordpressclient.data.Article

@Composable
fun HomeScreen(navController: NavController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Search + Notification
        item {
            TopBar(
                modifier = Modifier.padding(top = 4.dp)
            )
        }

        // Breaking News Section title
        item {
            SectionHeader(
                title = "Breaking News",
                onViewAllClick = { }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        // Featured Article (bạn có thể thêm onClick giống SuggestedItem nếu muốn)
        item {
            val featured = sampleArticles.first()
            FeaturedCard(
                title = featured.title,
                author = featured.author,
                imageUrl = featured.imageUrl
            )
        }

        // Recommendation Section title
        item {
            SectionHeader(
                title = "Recommendation",
                onViewAllClick = { }
            )
        }

        // Suggested articles list
        items(sampleArticles.drop(1)) { article: Article ->
            SuggestedItem(
                title = article.title,
                date = article.date,
                views = article.views,
                imageUrl = article.imageUrl,
                onClick = {
                    // 👇 Lưu Article vào SavedStateHandle rồi điều hướng sang ArticleScreen
                    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                    navController.navigate("article")
                }
            )
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    onViewAllClick: () -> Unit = {},
    viewAllColor: Color = Color(0xFF1E88E5)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = onViewAllClick) {
            Text(
                text = "View all",
                fontSize = 14.sp,
                color = viewAllColor
            )
        }
    }
}

@Composable
fun TopBar(
    query: String = "",
    onQueryChanged: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
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
