package com.example.wordpressclient.ui.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
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
        NotificationItem(
            "Alice", "liked", "UX Writing and UX Design", "2m ago",
            R.drawable.alice, R.drawable.write
        ),
        NotificationItem(
            "Bob", "commented on", "Building a successful Design System", "10m ago",
            R.drawable.bob, R.drawable.design
        ),
        NotificationItem(
            "Charlie", "started following", "you", "1h ago",
            R.drawable.charlie, R.drawable.cr7
        ),
        NotificationItem(
            "Selena", "liked", "Visiting towards the nature all alone", "2h ago",
            R.drawable.selena, R.drawable.nature
        ),
        NotificationItem(
            "David", "shared", "The curious case of Instagram comments", "3h ago",
            R.drawable.david, R.drawable.insta
        ),
        NotificationItem(
            "Emma", "liked", "Creating effective prototype that works", "5h ago",
            R.drawable.emma, R.drawable.prototype
        ),
        NotificationItem(
            "Frank", "commented on", "Top 10 UI/UX mistakes to avoid", "8h ago",
            R.drawable.frank, R.drawable.uiux
        )

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
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(notifications) { notif ->
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        // 🔹 Thumbnail bài viết
                        Image(
                            painter = painterResource(id = notif.thumbnail),
                            contentDescription = null,
                            modifier = Modifier
                                .size(80.dp) // giữ thumbnail đồng nhất
                                .clip(RoundedCornerShape(8.dp))
                                .padding(end = 12.dp)
                        )

                        // 🔹 Nội dung text
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp), // chiều cao đồng nhất
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            // Dòng 1: tiêu đề
                            Text(
                                text = notif.target.uppercase(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                maxLines = 1
                            )

                            // Dòng 2: avatar + action
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Image(
                                    painter = painterResource(id = notif.avatar),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "${notif.user} ${notif.action}",
                                    fontSize = 14.sp,
                                    color = Color.DarkGray,
                                    maxLines = 1
                                )
                            }

                            // Dòng 3: thời gian
                            Text(
                                text = notif.time,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }

                    // Divider ngăn cách
                    Divider(
                        color = Color(0xFFE0E0E0),
                        thickness = 1.dp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}
