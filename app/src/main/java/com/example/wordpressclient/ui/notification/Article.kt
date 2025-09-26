package com.example.wordpressclient.ui.notification

data class NotificationItem(
    val user: String,      // tên người dùng
    val action: String,    // hành động (liked, commented, followed)
    val target: String,    // đối tượng (your post, your article…)
    val time: String,      // thời gian
    val avatar: Int        // resource id ảnh đại diện
)
