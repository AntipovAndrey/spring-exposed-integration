package com.github.antipovandrey.springexposed.api

import com.github.antipovandrey.springexposed.data.entity.Post
import com.github.antipovandrey.springexposed.data.entity.UserAccount
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 *  A controller for testing transactions get rollbacked
 */
@RestController
@RequestMapping("posts/secured")
class SecuredPostController {

    /**
     * Inserts a row in the db, then tries to find it. In the end throws an exception to get transaction rollbacked
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    fun addPostFailing(
        @RequestBody postRequest: PostRequest,
        @AuthenticationPrincipal userAccount: UserAccount?
    ): PostResponse {
        val createdPost = Post.new {
            title = "${postRequest.title}, by ${userAccount?.login}"
            content = postRequest.content
        }
        return createdPost.toResponse()
    }
}
