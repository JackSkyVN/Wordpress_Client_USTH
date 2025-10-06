package com.example.wordpressclient.ui.notification

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wordpressclient.R
import com.example.wordpressclient.data.Article
import com.example.wordpressclient.viewmodel.AlertsViewModel
import coil.compose.AsyncImage
import androidx.compose.ui.layout.ContentScale

@Composable
fun Alert(navController: NavController? = null) {
    val viewModel: AlertsViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

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

        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
            return@Column
        }

        if (uiState.error != null) {
            Card(colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = "Error: ${uiState.error}", color = Color.Red)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(uiState.items) { article ->
                Column(
                    modifier = Modifier.clickable {
                        navController?.currentBackStackEntry
                            ?.savedStateHandle
                            ?.set("article", article)
                        navController?.navigate("article")
                    }
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        AsyncImage(
                            model = article.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(80.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .padding(end = 12.dp)
                        )

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .height(80.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = article.title.uppercase(),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                maxLines = 1
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                AsyncImage(
                                    model = article.imageUrl,
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .size(24.dp)
                                        .clip(CircleShape)
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "WordPress News",
                                    fontSize = 14.sp,
                                    color = Color.DarkGray,
                                    maxLines = 1
                                )
                            }

                            Text(
                                text = article.date,
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }

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
