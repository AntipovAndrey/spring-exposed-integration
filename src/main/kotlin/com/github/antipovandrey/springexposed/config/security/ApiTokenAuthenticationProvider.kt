package com.github.antipovandrey.springexposed.config.security

import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.Authentication

/**
 *  Authentication provider that accepts an [ApiAuthenticationTokenHolder] from the context
 *  Extracts the found token and creates authenticated [ApiAuthenticationToken] with user principal
 */
class ApiTokenAuthenticationProvider(
    private val userRetriever: UserAccountSecurityService
) : AuthenticationProvider {

    override fun authenticate(authentication: Authentication): Authentication {
        val accountToken = (authentication as ApiAuthenticationTokenHolder).principal
        val userAccount = userRetriever.findAccountByToken(accountToken)
                ?: throw BadCredentialsException("Invalid token")
        return ApiAuthenticationToken(userAccount).apply { isAuthenticated = true }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return authentication == ApiAuthenticationTokenHolder::class.java
    }
}
