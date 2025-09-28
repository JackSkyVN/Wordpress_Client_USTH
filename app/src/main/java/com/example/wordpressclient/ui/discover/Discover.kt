package com.example.wordpressclient.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

// =============== MAIN DISCOVER SCREEN ===============
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Discover() {
    var query by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(all = 16.dp)
    ) {
        // ===== Tiêu đề =====
        Text(
            text = "Discover",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // ===== Search Bar (3 gạch + ô search + icon search) =====
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Icon 3 gạch (Filter menu)
            IconButton(onClick = { /* TODO: mở menu filter riêng */ }) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Filter",
                    tint = Color(0xFF1E88E5) // xanh blue
                )
            }

            // Ô search
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                placeholder = { Text(text = "Search articles, topics...") },
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 8.dp),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFF1E88E5),
                    unfocusedBorderColor = Color.Gray,
                    cursorColor = Color(0xFF1E88E5)
                )
            )

            // Icon search
            IconButton(onClick = { /* TODO: search action */ }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color(0xFF1E88E5)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // ===== Hàng filter (Author | Views | Time) =====
        FilterOptions()

        Spacer(modifier = Modifier.height(20.dp))

        // ===== Các gợi ý filter (tags) =====
        FilterSuggestions()

        Spacer(modifier = Modifier.height(20.dp))

        // ===== Trending Articles =====
        TrendingArticles()
    }
}

// =============== FILTER OPTIONS (Location | Price | Time) ===============
@Composable
fun FilterOptions() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterBox(text = "Author", modifier = Modifier.weight(1f))
        FilterBox(text = "Views", modifier = Modifier.weight(1f))
        FilterBox(text = "Time", modifier = Modifier.weight(1f))
    }
}

@Composable
fun FilterBox(text: String, modifier: Modifier = Modifier) {
    OutlinedButton(
        onClick = { /* TODO: xử lý khi bấm */ },
        modifier = modifier
            .height(40.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        )
    ) {
        Text(text = text, fontSize = 14.sp)
    }
}

// =============== FILTER SUGGESTIONS (chips) ===============
@Composable
@OptIn(ExperimentalLayoutApi::class)
fun FilterSuggestions() {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterChip(text = "Design")
        FilterChip(text = "Technology")
        FilterChip(text = "Travel")
        FilterChip(text = "UX/UI")
        FilterChip(text = "Saved")
        FilterChip(text = "Notifications")
        FilterChip(text = "Trending")
    }
}

@Composable
fun FilterChip(text: String) {
    Surface(
        color = Color(0xFF1E88E5).copy(alpha = 0.15f), // nền xanh nhạt
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(end = 4.dp)
    ) {
        Text(
            text = text,
            color = Color(0xFF1E88E5),
            fontSize = 14.sp,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
        )
    }
}

// =============== TRENDING ARTICLES ===============
@Composable
fun TrendingArticles() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
    ) {
        Text(
            text = "🔥 Trending Articles",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            items(listOf(
                Article("Top 10 UI/UX Trends in 2025", "2 min read", "🔥"),
                Article("Kotlin Multiplatform Explained", "5 min read", "📱"),
                Article("AI in Mobile Apps: What's Next?", "3 min read", "🤖"),
                Article("Building a Scalable Design System", "4 min read", "🎨"),
                Article("The Future of Cross-platform Apps", "6 min read", "🚀")
            )) { article ->
                ArticleCard(article)
            }
        }
    }
}

data class Article(
    val title: String,
    val subtitle: String,
    val icon: String
)

@Composable
fun ArticleCard(article: Article) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(
            text = article.icon,
            fontSize = 20.sp,
            modifier = Modifier.padding(end = 8.dp)
        )

        Column {
            Text(
                text = article.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = article.subtitle,
                fontSize = 13.sp,
                color = Color.Gray
            )
        }
    }
}

