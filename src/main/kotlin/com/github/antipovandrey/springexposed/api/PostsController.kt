package com.github.antipovandrey.springexposed.api

import com.github.antipovandrey.springexposed.data.entity.Post
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("posts")
class PostsController {

    @PostMapping
    fun addPost(@RequestBody postRequest: PostRequest): PostResponse {
        return Post.new {
            title = postRequest.title
            content = postRequest.content
        }.toResponse()
    }

    @GetMapping
    fun findAll(): Iterable<PostResponse> = Post.all().map { it.toResponse() }
}
