package com.example.wordpressclient.ui.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordpressclient.R

@Composable
fun Alert() {
    val notifications = listOf(
        NotificationItem("Alice", "liked", "UX Writing and UX Design", "2m ago", R.drawable.alice),
        NotificationItem("Bob", "commented on", "Building a successful Design System", "10m ago", R.drawable.bob),
        NotificationItem("Charlie", "started following", "you", "1h ago", R.drawable.charlie),
        NotificationItem("Selena", "liked", "Visiting towards the nature all alone", "2h ago", R.drawable.selena)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp)
    ) {
        Text(
            text = "Notifications",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(notifications) { notif ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                ) {
                    // 🔹 Dòng 1: Tựa đề bài viết (IN HOA + ĐẬM)
                    Text(
                        text = notif.target.uppercase(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )

                    // 🔹 Dòng 2: Avatar + hành động
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(bottom = 4.dp)
                    ) {
                        Image(
                            painter = painterResource(id = notif.avatar),
                            contentDescription = null,
                            modifier = Modifier
                                .size(28.dp)
                                .clip(CircleShape)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "${notif.user} ${notif.action}",
                            fontSize = 14.sp,
                            color = Color.DarkGray
                        )
                    }

                    // 🔹 Dòng 3: Thời gian
                    Text(
                        text = notif.time,
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}
