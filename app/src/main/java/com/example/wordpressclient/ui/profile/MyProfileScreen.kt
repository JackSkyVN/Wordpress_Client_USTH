package com.example.wordpressclient.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
fun MyProfileScreen(
    onEditClick: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Avatar + Info
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.avatar),
                contentDescription = "Profile",
                modifier = Modifier
                    .size(90.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Charlotte King", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Text("@johnkinggraphics", color = Color.Gray, fontSize = 14.sp)

            Spacer(modifier = Modifier.height(12.dp))

            // Nút Edit Profile màu cam nhạt
            Text(
                text = "Edit Profile",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color(0xFFFFA726)) // Cam nhạt
                    .clickable { onEditClick() }
                    .padding(horizontal = 24.dp, vertical = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Menu
        ProfileMenuItem("Favourites")
        ProfileMenuItem("Downloads")
        ProfileMenuItem("Language")
        ProfileMenuItem("Location")
        ProfileMenuItem("Subscription")
        ProfileMenuItem("Clear cache")
        ProfileMenuItem("Clear history")
        ProfileMenuItem("Log out", isLogout = true)
    }
}

@Composable
fun ProfileMenuItem(title: String, isLogout: Boolean = false) {
    Text(
        text = title,
        fontSize = 16.sp,
        color = if (isLogout) Color.Red else Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    )
}
