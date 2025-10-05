package com.example.wordpressclient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordpressclient.data.Article
import com.example.wordpressclient.data.model.WpPost
import com.example.wordpressclient.data.model.WpTag
import com.example.wordpressclient.repository.WordPressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class DiscoverUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val searchQuery: String = "",
    val searchResults: List<Article> = emptyList(),
    val trendingPosts: List<Article> = emptyList(),
    val popularTags: List<WpTag> = emptyList(),
    val selectedFilter: FilterType = FilterType.All
)

enum class FilterType {
    All, Author, Views, Time, Category, Tag
}

class DiscoverViewModel : ViewModel() {
    
    private val repository = WordPressRepository()
    
    private val _uiState = MutableStateFlow(DiscoverUiState())
    val uiState: StateFlow<DiscoverUiState> = _uiState.asStateFlow()
    
    init {
        loadTrendingPosts()
        loadPopularTags()
    }
    
    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }
    
    fun performSearch(query: String = _uiState.value.searchQuery) {
        if (query.isBlank()) {
            clearSearchResults()
            return
        }
        
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val result = repository.searchPosts(query)
                result.fold(
                    onSuccess = { wpPosts ->
                        val articles = wpPosts.map { wpPost ->
                            convertWpPostToArticle(wpPost)
                        }
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            searchResults = articles,
                            error = null
                        )
                    },
                    onFailure = { exception ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = exception.message ?: "Search failed"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Search failed"
                )
            }
        }
    }
    
    fun clearSearchResults() {
        _uiState.value = _uiState.value.copy(
            searchResults = emptyList(),
            searchQuery = ""
        )
    }
    
    fun loadTrendingPosts() {
        viewModelScope.launch {
            try {
                val result = repository.getTrendingPosts(perPage = 5)
                result.fold(
                    onSuccess = { wpPosts ->
                        val articles = wpPosts.map { wpPost ->
                            convertWpPostToArticle(wpPost)
                        }
                        _uiState.value = _uiState.value.copy(trendingPosts = articles)
                    },
                    onFailure = { 
                        // Don't show error for trending posts, just keep empty list
                    }
                )
            } catch (e: Exception) {
                // Don't show error for trending posts, just keep empty list
            }
        }
    }
    
    fun loadPopularTags() {
        viewModelScope.launch {
            try {
                val result = repository.getTags(perPage = 10)
                result.fold(
                    onSuccess = { tags ->
                        _uiState.value = _uiState.value.copy(popularTags = tags)
                    },
                    onFailure = { 
                        // Don't show error for tags, just keep empty list
                    }
                )
            } catch (e: Exception) {
                // Don't show error for tags, just keep empty list
            }
        }
    }
    
    fun searchByTag(tagId: Int, tagName: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val result = repository.getPostsByTag(tagId)
                result.fold(
                    onSuccess = { wpPosts ->
                        val articles = wpPosts.map { wpPost ->
                            convertWpPostToArticle(wpPost)
                        }
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            searchResults = articles,
                            searchQuery = "#$tagName",
                            error = null
                        )
                    },
                    onFailure = { exception ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = exception.message ?: "Failed to load posts for tag"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load posts for tag"
                )
            }
        }
    }
    
    fun searchByCategory(categoryId: Int, categoryName: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val result = repository.getPostsByCategory(categoryId)
                result.fold(
                    onSuccess = { wpPosts ->
                        val articles = wpPosts.map { wpPost ->
                            convertWpPostToArticle(wpPost)
                        }
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            searchResults = articles,
                            searchQuery = categoryName,
                            error = null
                        )
                    },
                    onFailure = { exception ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = exception.message ?: "Failed to load posts for category"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Failed to load posts for category"
                )
            }
        }
    }
    
    fun setFilter(filter: FilterType) {
        _uiState.value = _uiState.value.copy(selectedFilter = filter)
        // You can implement different sorting logic based on filter type
    }
    
    private fun convertWpPostToArticle(wpPost: WpPost): Article {
        // Extract clean text from HTML content
        val cleanContent = wpPost.content.rendered
            .replace(Regex("<[^>]*>"), "") // Remove HTML tags
            .replace(Regex("&[^;]+;"), "") // Remove HTML entities
            .trim()
        
        // Extract image URL from featured media or content
        val imageUrl = if (wpPost.featuredMedia != 0) {
            "https://wordpress.org/news/wp-json/wp/v2/media/${wpPost.featuredMedia}"
        } else {
            "https://picsum.photos/600/400?random=${wpPost.id}" // Fallback image
        }
        
        // Format date
        val formattedDate = try {
            val date = java.time.LocalDateTime.parse(wpPost.date.replace("T", " ").substring(0, 19))
            val formatter = java.time.format.DateTimeFormatter.ofPattern("MMM dd, yyyy")
            date.format(formatter)
        } catch (e: Exception) {
            "Recent"
        }
        
        return Article(
            title = wpPost.title.rendered.replace(Regex("<[^>]*>"), "").trim(),
            author = "WordPress News",
            date = formattedDate,
            views = "${(1000..10000).random()} views",
            imageUrl = imageUrl,
            content = cleanContent.take(500) + if (cleanContent.length > 500) "..." else "",
            topic = "WordPress"
        )
    }
}
