package com.github.antipovandrey.springexposed.config.security

import com.github.antipovandrey.springexposed.data.entity.UserAccount
import org.springframework.security.authentication.AbstractAuthenticationToken

/**
 *  Authentication token which will be accessible in the application
 */
class ApiAuthenticationToken(
    private val user: UserAccount
) : AbstractAuthenticationToken(null /*authorities*/) {

    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): UserAccount {
        return user
    }
}
