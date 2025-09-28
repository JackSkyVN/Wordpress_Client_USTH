package com.example.wordpressclient.ui.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.wordpressclient.R

@Composable
fun EditProfileScreen(
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    var name by remember { mutableStateOf("Charlotte King") }
    var email by remember { mutableStateOf("johnkinggraphics@gmail.com") }
    var username by remember { mutableStateOf("@johnkinggraphics") }
    var password by remember { mutableStateOf("123456") }
    var phone by remember { mutableStateOf("6989532") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Top bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("←", fontSize = 24.sp, modifier = Modifier.clickable { onBack() })
            Text("✔", fontSize = 24.sp, modifier = Modifier.clickable { onSave() })
        }

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Profile",
            modifier = Modifier.size(90.dp).clip(CircleShape)
        )

        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text("Name") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email address") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("User name") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Password") }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(value = phone, onValueChange = { phone = it }, label = { Text("Phone number") }, modifier = Modifier.fillMaxWidth())
    }
}
