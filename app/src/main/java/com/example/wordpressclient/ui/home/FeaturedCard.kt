package com.example.wordpressclient.ui.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
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
    imageUrl: String,
    topic: String,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(240.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() }
    ) {
        // Ảnh nền
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )

        // Gradient mờ từ dưới lên
        Box(
            modifier = Modifier
                .matchParentSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.7f)
                        ),
                        startY = 100f
                    )
                )
        )

        // 👇 Topic chip ở góc trên trái
        Surface(
            shape = RoundedCornerShape(50),
            color = Color(0xFFFFA726), // 👈 màu nền cam
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(12.dp)
        ) {
            Text(
                text = topic,
                color = Color.White, // chữ trắng nổi bật
                fontSize = 12.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
            )
        }

        // Text đè lên ảnh
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            )

            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "By $author",
                style = MaterialTheme.typography.bodySmall.copy(
                    color = Color.White.copy(alpha = 0.9f)
                )
            )
        }
    }
}
