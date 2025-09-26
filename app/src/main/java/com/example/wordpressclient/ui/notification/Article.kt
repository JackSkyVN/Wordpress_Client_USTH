

package com.example.wordpressclient.ui.notification

data class Article(
    val title: String,
    val category: String,
    val time: String,
    val imageRes: Int,
    var isBookmarked: Boolean = false
)
