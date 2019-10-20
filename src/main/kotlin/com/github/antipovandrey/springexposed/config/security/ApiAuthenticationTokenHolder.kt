package com.github.antipovandrey.springexposed.config.security

import org.springframework.security.authentication.AbstractAuthenticationToken

/**
 *  A holder of a filtered form header API token
 */
class ApiAuthenticationTokenHolder(
    private val token: String
) : AbstractAuthenticationToken(null) {

    override fun getCredentials(): Any? {
        return null
    }

    override fun getPrincipal(): String {
        return token
    }
}
