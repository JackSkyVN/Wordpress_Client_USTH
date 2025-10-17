package com.example.wordpressclient.ui.home

import coil.compose.SubcomposeAsyncImage
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import coil.compose.rememberAsyncImagePainter

@Composable
fun SuggestedItem(
    title: String,
    date: String,
    views: String,
    imageUrl: String,
    topic: String,
    onClick: () -> Unit
) {
    // Row for Img + Content
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(vertical = 4.dp)
    ) {
        // Article image
        SubcomposeAsyncImage(
            model = imageUrl,
            contentDescription = title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight()           // Height = row
                .clip(RoundedCornerShape(8.dp))
                .clickable { onClick() },
            loading = {},
            error = {
                // If error, random a photo
                SubcomposeAsyncImage(
                    model = "https://picsum.photos/400/300?random=${title.hashCode()}",
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .width(100.dp)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(8.dp))
                )
            }
        )

        Spacer(modifier = Modifier.width(12.dp))

        // Column contains Content
        Column(
            modifier = Modifier
                .fillMaxHeight() // Height = row
                .weight(1f)
        ) {
            // Row contains Topic + Date + View
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween, // Horizontal
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Topic tag
                Surface(
                    shape = RoundedCornerShape(50),
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f) // Blur color
                ) {
                    Text(
                        text = topic,
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.primary,
                            fontWeight = FontWeight.SemiBold
                        ),
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }

                // Date + View
                Text(
                    text = "$date  •  $views",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray)
                )
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Title (Clickable)
            Text(
                text = title,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,   // If too long -> show "..."
                modifier = Modifier.clickable { onClick() } // Click event
            )
        }
    }
}
