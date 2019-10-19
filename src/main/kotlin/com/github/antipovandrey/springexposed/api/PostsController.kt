package com.github.antipovandrey.springexposed.api

import com.github.antipovandrey.springexposed.data.entity.Post
import org.springframework.web.bind.annotation.*

/**
 *  A normal controller without Transactional annotation
 */
@RestController
@RequestMapping("posts")
class PostsController {

    /**
     * Adds a post under transaction created by the request interceptor
     */
    @PostMapping
    fun addPost(@RequestBody postRequest: PostRequest): PostResponse {
        return Post.new {
            title = postRequest.title
            content = postRequest.content
        }.toResponse()
    }

    /**
     *  Returns all posts under transaction created by the request interceptor
     */
    @GetMapping
    fun findAll(): Iterable<PostResponse> = Post.all().map { it.toResponse() }
}
