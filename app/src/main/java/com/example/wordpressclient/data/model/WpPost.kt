package com.example.wordpressclient.data.model

import com.google.gson.annotations.SerializedName

data class WpPost(
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
    val content: WpContent,
    val excerpt: WpExcerpt,
    val author: Int,
    @SerializedName("featured_media")
    val featuredMedia: Int,
    @SerializedName("comment_status")
    val commentStatus: String,
    @SerializedName("ping_status")
    val pingStatus: String,
    val sticky: Boolean,
    val template: String,
    val format: String,
    val meta: Map<String, Any>,
    val categories: List<Int>,
    val tags: List<Int>,
    val links: WpLinks
)

data class WpGuid(
    val rendered: String
)

data class WpTitle(
    val rendered: String
)

data class WpContent(
    val rendered: String,
    val protected: Boolean
)

data class WpExcerpt(
    val rendered: String,
    val protected: Boolean
)

data class WpLinks(
    val self: List<WpLink>,
    val collection: List<WpLink>,
    val about: List<WpLink>,
    val author: List<WpLink>,
    val replies: List<WpLink>,
    @SerializedName("version-history")
    val versionHistory: List<WpLink>,
    @SerializedName("predecessor-version")
    val predecessorVersion: List<WpLink>,
    @SerializedName("wp:featuredmedia")
    val featuredMedia: List<WpLink>,
    @SerializedName("wp:attachment")
    val attachment: List<WpLink>,
    @SerializedName("wp:term")
    val term: List<WpLink>,
    val curies: List<WpCurie>
)

data class WpLink(
    val href: String,
    @SerializedName("targetHints")
    val targetHints: WpTargetHints? = null
)

data class WpTargetHints(
    val allow: List<String>? = null
)

data class WpCurie(
    val name: String,
    val href: String,
    val templated: Boolean
)
