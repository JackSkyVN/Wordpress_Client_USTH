package com.example.wordpressclient.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun FeaturedCard(
    title: String,
    author: String,
    date: String,
    views: String,
    imageUrl: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp) // tạo khoảng cách với divider
    ) {
        // Ảnh bo góc
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Tiêu đề
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                color = Color.Black
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Dòng Author + Date + Views
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "By: $author",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color(0xFF1565C0),
                    fontWeight = FontWeight.Medium
                )
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "$date  •  $views",
                style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Đường kẻ ngang
        Divider(
            color = Color.Gray.copy(alpha = 0.4f), // giống màu date/views
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth(1.2f) // chỉ dài bằng nội dung, không full màn hình
        )
    }
}
