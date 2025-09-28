package com.example.wordpressclient.data.model

import com.google.gson.annotations.SerializedName

data class WpMedia(
    val id: Int,
    val date: String,
    @SerializedName("date_gmt")
    val dateGmt: String,
    val guid: WpGuid,
    val modified: String,
    @SerializedName("modified_gmt")
    val modifiedGmt: String,
    val slug: String,
    val status: String,
    val type: String,
    val link: String,
    val title: WpTitle,
    val author: Int,
    @SerializedName("comment_status")
    val commentStatus: String,
    @SerializedName("ping_status")
    val pingStatus: String,
    val template: String,
    val meta: Map<String, Any>,
    val description: WpContent,
    val caption: WpContent,
    @SerializedName("alt_text")
    val altText: String,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("mime_type")
    val mimeType: String,
    @SerializedName("media_details")
    val mediaDetails: WpMediaDetails,
    val source_url: String,
    @SerializedName("_links")
    val links: WpMediaLinks
)

data class WpMediaDetails(
    val width: Int,
    val height: Int,
    val file: String,
    val sizes: Map<String, WpMediaSize>
)

data class WpMediaSize(
    val file: String,
    val width: Int,
    val height: Int,
    @SerializedName("mime_type")
    val mimeType: String,
    val source_url: String
)

data class WpMediaLinks(
    val self: List<WpLink>,
    val collection: List<WpLink>,
    val about: List<WpLink>,
    val author: List<WpLink>,
    val replies: List<WpLink>,
    val curies: List<WpCurie>
)
