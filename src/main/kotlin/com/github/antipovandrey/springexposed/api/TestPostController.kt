package com.github.antipovandrey.springexposed.api

import com.github.antipovandrey.springexposed.data.PostTable
import com.github.antipovandrey.springexposed.data.entity.Post
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *  A controller for testing transactions get rollbacked
 */
@RestController
@RequestMapping("posts/test")
class TestPostController {

    /**
     * Inserts a row in the db, then tries to find it. In the end throws an exception to get transaction rollbacked
     */
    @PostMapping
    fun addPostFailing(@RequestBody postRequest: PostRequest): PostResponse {
        val createdPost = Post.new {
            title = postRequest.title
            content = postRequest.content
        }
        Post.find { PostTable.id eq createdPost.id }.singleOrNull()
                ?: error("Entity created in transaction but not found")
        throw Exception("Now make sure entity with id ${createdPost.id.value} is missing in the database")
    }
}
