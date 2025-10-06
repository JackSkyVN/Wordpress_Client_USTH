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

data class AlertsUiState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val items: List<Article> = emptyList()
)

class AlertsViewModel : ViewModel() {

    private val repository = WordPressRepository()

    private val _uiState = MutableStateFlow(AlertsUiState(isLoading = true))
    val uiState: StateFlow<AlertsUiState> = _uiState.asStateFlow()

    init {
        loadAlerts()
    }

    fun loadAlerts() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            val result = repository.getPosts(perPage = 20, page = 1)
            result.fold(
                onSuccess = { posts ->
                    val mapped = posts.map { convertWpPostToArticle(it) }
                    _uiState.value = AlertsUiState(isLoading = false, error = null, items = mapped)
                },
                onFailure = { e ->
                    _uiState.value = AlertsUiState(isLoading = false, error = e.message, items = emptyList())
                }
            )
        }
    }

    private fun convertWpPostToArticle(wpPost: WpPost): Article {
        val cleanContent = wpPost.excerpt.rendered
            .replace(Regex("<[^>]*>"), "")
            .replace(Regex("&[^;]+;"), "")
            .trim()

        val imageUrl = if (wpPost.featuredMedia != 0) {
            "https://wordpress.org/news/wp-json/wp/v2/media/${wpPost.featuredMedia}"
        } else {
            "https://picsum.photos/600/400?random=${wpPost.id}"
        }

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
            views = "",
            imageUrl = imageUrl,
            content = cleanContent,
            topic = "Alerts"
        )
    }
}







