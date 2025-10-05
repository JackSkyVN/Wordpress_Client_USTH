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

// ✅ dữ liệu người dùng
data class ProfileState(
    var name: String = "Duong Duc",
    var email: String = "duongduc123@gmail.com",
    var username: String = "@duc123",
    var password: String = "123456",
    var phone: String = "6989532"
)

@Composable
fun MyProfileScreen(
    onEditClick: () -> Unit = {}
) {
    val profile = ProfileState()
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Avatar
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile",
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Tên + username
        Text(profile.name, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text(profile.username, color = Color.Gray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(12.dp))

        // Nút Edit Profile
        Text(
            text = "Edit Profile",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFFFFA726)) // cam nhạt
                .clickable { onEditClick() }
                .padding(horizontal = 24.dp, vertical = 8.dp)
        )

        // Danh sách menu
        Spacer(modifier = Modifier.height(24.dp))
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
