package com.example.wordpressclient.data.model

import com.google.gson.annotations.SerializedName

data class WpTag(
    val id: Int,
    val count: Int,
    val description: String,
    val link: String,
    val name: String,
    val slug: String,
    val taxonomy: String,
    @SerializedName("meta")
    val meta: List<Any> = emptyList(),
    @SerializedName("_links")
    val links: WpLinks
)
