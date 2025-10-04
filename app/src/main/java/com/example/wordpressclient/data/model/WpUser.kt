package com.example.wordpressclient.data.model

import com.google.gson.annotations.SerializedName

data class WpUser(
    val id: Int,
    val name: String,
    val url: String,
    val description: String,
    val link: String,
    val slug: String,
    @SerializedName("avatar_urls")
    val avatarUrls: Map<String, String>,
    val meta: Map<String, Any>,
    @SerializedName("_links")
    val links: WpUserLinks
)

data class WpUserLinks(
    val self: List<WpLink>,
    val collection: List<WpLink>
)
