package com.example.wordpressclient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wordpressclient.data.Article
import com.example.wordpressclient.data.model.WpPost
import com.example.wordpressclient.repository.WordPressRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class WordPressUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val posts: List<Article> = emptyList(),
    val featuredPost: Article? = null
)

class WordPressViewModel : ViewModel() {
    
    private val repository = WordPressRepository()
    
    private val _uiState = MutableStateFlow(WordPressUiState())
    val uiState: StateFlow<WordPressUiState> = _uiState.asStateFlow()
    
    init {
        loadPosts()
    }
    
    fun loadPosts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                val result = repository.getPosts(perPage = 20, page = 1)
                result.fold(
                    onSuccess = { wpPosts ->
                        val articles = wpPosts.map { wpPost ->
                            convertWpPostToArticle(wpPost)
                        }
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            posts = articles,
                            featuredPost = articles.firstOrNull(),
                            error = null
                        )
                    },
                    onFailure = { exception ->
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = exception.message ?: "Unknown error occurred"
                        )
                    }
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    error = e.message ?: "Unknown error occurred"
                )
            }
        }
    }
    
    fun refreshPosts() {
        loadPosts()
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
            author = "WordPress News", // We'll fetch author details separately if needed
            date = formattedDate,
            views = "${(1000..10000).random()} views", // Random view count for demo
            imageUrl = imageUrl,
            content = cleanContent.take(500) + if (cleanContent.length > 500) "..." else "",
            topic = "WordPress" // Default topic
        )
    }
}
