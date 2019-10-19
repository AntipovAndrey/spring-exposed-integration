package com.github.antipovandrey.springexposed.api

import com.github.antipovandrey.springexposed.data.entity.Post

data class PostRequest(
    val title: String,
    val content: String
)

data class PostResponse(
    val id: Int,
    val title: String,
    val content: String
)

fun Post.toResponse() = PostResponse(
        id = this.id.value,
        title = this.title,
        content = this.content
)
