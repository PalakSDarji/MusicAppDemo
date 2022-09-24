package com.palak.applemusicappdemo.models

data class CommentsResponseItem(
    val body: String,
    val email: String,
    val id: Int,
    val name: String,
    val postId: Int
)