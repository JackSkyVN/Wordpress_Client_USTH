package com.example.wordpressclient.data.model

import com.google.gson.annotations.SerializedName

data class WpCategory(
    val id: Int,
    val count: Int,
    val description: String,
    val link: String,
    val name: String,
    val slug: String,
    val taxonomy: String,
    val parent: Int,
    val meta: Map<String, Any>,
    @SerializedName("_links")
    val links: WpCategoryLinks
)

data class WpCategoryLinks(
    val self: List<WpLink>,
    val collection: List<WpLink>,
    val about: List<WpLink>,
    @SerializedName("wp:post_type")
    val postType: List<WpLink>,
    val curies: List<WpCurie>
)
