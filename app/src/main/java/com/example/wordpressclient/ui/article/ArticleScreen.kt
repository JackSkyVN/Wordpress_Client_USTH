package com.example.wordpressclient.ui.article

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.wordpressclient.data.Article

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleScreen(
    navController: NavController,
    article: Article,
    topBarTriggerOffset: Dp = 130.dp // ngưỡng hiển thị AppBar
) {
    val scrollState = rememberScrollState()

    // Convert dp sang px để so sánh với scrollState.value
    val thresholdPx = with(LocalDensity.current) { topBarTriggerOffset.toPx() }
    val showAppBar = remember {
        derivedStateOf { scrollState.value >= thresholdPx }
    }

    Scaffold(
        topBar = {
            AnimatedVisibility(
                visible = showAppBar.value,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                TopAppBar(
                    title = {
                        Text(
                            text = article.title,
                            maxLines = 1,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    },
                    navigationIcon = {
                        IconButton(onClick = { navController.popBackStack() }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                        titleContentColor = Color.Black,
                        navigationIconContentColor = Color.Black
                    )
                )
            }
        }
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize()) {
            // Ảnh nền cố định
            Image(
                painter = rememberAsyncImagePainter(article.imageUrl),
                contentDescription = article.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .align(Alignment.TopCenter)
            )

            // Nội dung cuộn
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(top = 220.dp, bottom = paddingValues.calculateBottomPadding())
            ) {
                Surface(
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    color = Color.White,
                    tonalElevation = 4.dp,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        Text(
                            text = article.title,
                            style = MaterialTheme.typography.titleLarge.copy(
                                fontWeight = FontWeight.Bold,
                                fontSize = 22.sp
                            )
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "By ${article.author}",
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = article.date,
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = article.views,
                                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = article.content,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 18.sp,
                                lineHeight = 26.sp
                            )
                        )
                    }
                }
            }

            // Nút back nổi trên ảnh khi AppBar chưa hiện
            if (!showAppBar.value) {
                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier
                        .padding(16.dp)
                        .align(Alignment.TopStart)
                        .zIndex(10f) // đảm bảo luôn nằm trên cùng
                ) {
                    Icon(
                        Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White // trắng để nổi trên ảnh
                    )
                }
            }
        }
    }
}
