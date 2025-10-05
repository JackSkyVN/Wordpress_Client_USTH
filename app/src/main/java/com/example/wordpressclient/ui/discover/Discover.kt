package com.example.wordpressclient.ui.discover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.wordpressclient.data.Article
import com.example.wordpressclient.viewmodel.DiscoverViewModel
import com.example.wordpressclient.viewmodel.FilterType

// =============== MAIN DISCOVER SCREEN WITH API INTEGRATION ===============
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Discover(navController: NavController? = null) {
    val viewModel: DiscoverViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // ===== Header =====
        item {
            Text(
                text = "Discover",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // ===== Search Bar =====
        item {
            SearchBar(
                query = uiState.searchQuery,
                onQueryChange = { viewModel.updateSearchQuery(it) },
                onSearchClick = { viewModel.performSearch() },
                onClearClick = { viewModel.clearSearchResults() }
            )
        }

        // ===== Loading State =====
        if (uiState.isLoading) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF1E88E5))
                }
            }
        }

        // ===== Error State =====
        if (uiState.error != null) {
            item {
                ErrorCard(
                    error = uiState.error!!,
                    onRetryClick = { 
                        if (uiState.searchQuery.isNotBlank()) {
                            viewModel.performSearch()
                        } else {
                            viewModel.loadTrendingPosts()
                        }
                    }
                )
            }
        }

        // ===== Search Results =====
        if (uiState.searchResults.isNotEmpty()) {
            item {
                Text(
                    text = "Search Results (${uiState.searchResults.size})",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }
            
            items(uiState.searchResults) { article ->
                SearchResultCard(
                    article = article,
                    onClick = { 
                        navController?.navigate("article") {
                            // Pass article data through saved state
                        }
                    }
                )
            }
        }

        // ===== Filter Options (only show when not searching) =====
        if (uiState.searchResults.isEmpty() && !uiState.isLoading) {
            item {
                FilterOptions(
                    selectedFilter = uiState.selectedFilter,
                    onFilterClick = { viewModel.setFilter(it) }
                )
            }

            // ===== Popular Tags =====
            if (uiState.popularTags.isNotEmpty()) {
                item {
                    PopularTagsSection(
                        tags = uiState.popularTags,
                        onTagClick = { tag ->
                            viewModel.searchByTag(tag.id, tag.name)
                        }
                    )
                }
            }

            // ===== Trending Articles =====
            if (uiState.trendingPosts.isNotEmpty()) {
                item {
                    TrendingArticlesSection(
                        articles = uiState.trendingPosts,
                        onArticleClick = { article ->
                            navController?.navigate("article") {
                                // Pass article data through saved state
                            }
                        }
                    )
                }
            }
        }
    }
}

// =============== SEARCH BAR ===============
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchClick: () -> Unit,
    onClearClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        // Filter icon
        IconButton(onClick = { /* TODO: Filter menu */ }) {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Filter",
                tint = Color(0xFF1E88E5)
            )
        }

        // Search field
        OutlinedTextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = { Text(text = "Search articles, topics...") },
            modifier = Modifier.weight(1f),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFF1E88E5),
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color(0xFF1E88E5)
            ),
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = onClearClick) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear",
                            tint = Color.Gray
                        )
                    }
                }
            }
        )

        // Search button
        IconButton(onClick = onSearchClick) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFF1E88E5)
            )
        }
    }
}

// =============== ERROR CARD ===============
@Composable
fun ErrorCard(
    error: String,
    onRetryClick: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFEBEE)),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Error",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Red
            )
            Text(
                text = error,
                fontSize = 14.sp,
                color = Color.Red,
                modifier = Modifier.padding(vertical = 8.dp)
            )
            Button(
                onClick = onRetryClick,
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1E88E5))
            ) {
                Text("Retry", color = Color.White)
            }
        }
    }
}

// =============== SEARCH RESULT CARD ===============
@Composable
fun SearchResultCard(
    article: Article,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = article.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = article.content.take(100) + if (article.content.length > 100) "..." else "",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = article.author,
                    fontSize = 12.sp,
                    color = Color(0xFF1E88E5)
                )
                Text(
                    text = article.date,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

// =============== FILTER OPTIONS ===============
@Composable
fun FilterOptions(
    selectedFilter: FilterType,
    onFilterClick: (FilterType) -> Unit
) {
    Text(
        text = "Filter by:",
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black,
        modifier = Modifier.padding(bottom = 8.dp)
    )
    
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        FilterBox(
            text = "All",
            isSelected = selectedFilter == FilterType.All,
            onClick = { onFilterClick(FilterType.All) },
            modifier = Modifier.weight(1f)
        )
        FilterBox(
            text = "Author",
            isSelected = selectedFilter == FilterType.Author,
            onClick = { onFilterClick(FilterType.Author) },
            modifier = Modifier.weight(1f)
        )
        FilterBox(
            text = "Views",
            isSelected = selectedFilter == FilterType.Views,
            onClick = { onFilterClick(FilterType.Views) },
            modifier = Modifier.weight(1f)
        )
        FilterBox(
            text = "Time",
            isSelected = selectedFilter == FilterType.Time,
            onClick = { onFilterClick(FilterType.Time) },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun FilterBox(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isSelected) Color(0xFF1E88E5) else Color.White,
            contentColor = if (isSelected) Color.White else Color.Black
        )
    ) {
        Text(text = text, fontSize = 14.sp)
    }
}

// =============== POPULAR TAGS SECTION ===============
@Composable
fun PopularTagsSection(
    tags: List<com.example.wordpressclient.data.model.WpTag>,
    onTagClick: (com.example.wordpressclient.data.model.WpTag) -> Unit
) {
    Column {
        Text(
            text = "🏷️ Popular Tags",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(tags.take(10)) { tag ->
                TagCard(
                    tag = tag,
                    onClick = { onTagClick(tag) }
                )
            }
        }
    }
}

@Composable
fun TagCard(
    tag: com.example.wordpressclient.data.model.WpTag,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        color = Color(0xFF1E88E5).copy(alpha = 0.15f),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier.padding(horizontal = 4.dp)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "#${tag.name}",
                color = Color(0xFF1E88E5),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = " (${tag.count})",
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
    }
}

// =============== TRENDING ARTICLES SECTION ===============
@Composable
fun TrendingArticlesSection(
    articles: List<Article>,
    onArticleClick: (Article) -> Unit
) {
    Column {
        Text(
            text = "🔥 Trending Articles",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(articles) { article ->
                TrendingArticleCard(
                    article = article,
                    onClick = { onArticleClick(article) }
                )
            }
        }
    }
}

@Composable
fun TrendingArticleCard(
    article: Article,
    onClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "🔥",
                fontSize = 20.sp,
                modifier = Modifier.padding(end = 12.dp)
            )
            
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = article.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = article.date,
                    fontSize = 13.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

