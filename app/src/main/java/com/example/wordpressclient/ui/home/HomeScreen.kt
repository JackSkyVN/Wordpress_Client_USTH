package com.example.wordpressclient.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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

        // Featured Section (big card)
        item {
            FeaturedCard(
                title = "New VR Headsets That Will Shape the Metaverse",
                author = "Mason Eduard",
                date = "Jan 3, 2022",
                views = "3344 views",
                imageUrl = "https://picsum.photos/600/400" // demo ảnh
            )
        }

        // Recommendation Section title
        item {
            SectionHeader("Recommendation")
        }

        items(5) { index ->
            SuggestedItem(
                title = "Suggested article #$index",
                author = "Author $index",
                date = "Jan ${index + 1}, 2022",
                views = "${1000 * (index + 1)} views",
                imageUrl = "https://picsum.photos/300/200?random=$index"
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
                    modifier = Modifier.size(20.dp) // giữ icon nhỏ để không đẩy text xuống
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(56.dp), // 56.dp là chiều cao tiêu chuẩn tránh cắt chữ
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
