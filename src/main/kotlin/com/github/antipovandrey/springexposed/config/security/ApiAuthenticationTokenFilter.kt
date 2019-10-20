package com.github.antipovandrey.springexposed.config.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

/**
 * Servlet filter that populates [ApiAuthenticationTokenHolder] into security context
 * from the header if found any
 */
class ApiAuthenticationTokenFilter(
    private val authHeaderName: String
) : GenericFilterBean() {

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpRequest = request as HttpServletRequest
        val context = SecurityContextHolder.getContext()
        val headerToken: String? = httpRequest.getHeader(authHeaderName)

        if (headerToken != null && context.authentication == null) {
            val authentication = ApiAuthenticationTokenHolder(headerToken)
            context.authentication = authentication
        }

        chain.doFilter(request, response)
    }
}
