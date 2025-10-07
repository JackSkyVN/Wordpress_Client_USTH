package com.example.wordpressclient.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.wordpressclient.data.Article
import com.example.wordpressclient.viewmodel.WordPressViewModel
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel: WordPressViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()
    
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        // Search + Notification
        item {
            var query by remember { mutableStateOf("") }
            TopBar(
                query = query,
                onQueryChanged = { q ->
                    query = q
                },
                modifier = Modifier.padding(top = 4.dp),
                onRefreshClick = { viewModel.refreshPosts() },
                onSearchClick = { if (query.isBlank()) viewModel.refreshPosts() else viewModel.searchPosts(query) }
            )
        }

        // Loading state
        if (uiState.isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }
        }

        // Error state
        if (uiState.error != null) {
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE))
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Error loading posts",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color(0xFFD32F2F)
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = uiState.error ?: "Unknown error",
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color(0xFFD32F2F)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = { viewModel.refreshPosts() }) {
                            Text("Retry")
                        }
                    }
                }
            }
        }

        // Breaking News Section title
        item {
            SectionHeader(
                title = "Breaking News",
                onViewAllClick = { }
            )
            Spacer(modifier = Modifier.height(4.dp))
        }

        // Featured Article
        uiState.featuredPost?.let { featured ->
            item {
                FeaturedCard(
                    title = featured.title,
                    author = featured.author,
                    imageUrl = featured.imageUrl,
                    topic = featured.topic,
                    onClick = {
                        navController.currentBackStackEntry?.savedStateHandle?.set("article", featured)
                        navController.navigate("article")
                    }
                )
            }
        }

        // Recommendation Section title
        item {
            SectionHeader(
                title = "Recommendation",
                onViewAllClick = { }
            )
        }

        // Suggested articles list
        items(uiState.posts.drop(1)) { article: Article ->
            SuggestedItem(
                title = article.title,
                date = article.date,
                views = article.views,
                imageUrl = article.imageUrl,
                topic = article.topic,
                onClick = {
                    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
                    navController.navigate("article")
                }
            )
        }
    }
}

@Composable
fun SectionHeader(
    title: String,
    onViewAllClick: () -> Unit = {},
    viewAllColor: Color = Color(0xFFFFA726)
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            fontSize = 22.sp
        )

        Spacer(modifier = Modifier.weight(1f))

        TextButton(onClick = onViewAllClick) {
            Text(
                text = "View all",
                fontSize = 14.sp,
                color = viewAllColor
            )
        }
    }
}

@Composable
fun TopBar(
    query: String = "",
    onQueryChanged: (String) -> Unit = {},
    onRefreshClick: () -> Unit = {},
    onSearchClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChanged,
            singleLine = true,
            maxLines = 1,
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
            placeholder = { Text("Search...", fontSize = 16.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier.size(20.dp)
                )
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClick() }),
            modifier = Modifier
                .weight(1f)
                .height(56.dp),
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xFFF5F5F5),
                unfocusedContainerColor = Color(0xFFF5F5F5),
                disabledContainerColor = Color(0xFFF5F5F5),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.width(8.dp))

        IconButton(onClick = onSearchClick) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        }

        BadgedBox(
            badge = {
                Badge { Text("1") }
            }
        ) {
            IconButton(onClick = { /* TODO */ }) {
                Icon(
                    imageVector = Icons.Default.Notifications,
                    contentDescription = "Notifications"
                )
            }
        }
    }
}
