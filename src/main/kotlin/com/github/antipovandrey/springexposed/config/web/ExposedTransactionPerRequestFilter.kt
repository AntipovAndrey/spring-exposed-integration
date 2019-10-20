package com.github.antipovandrey.springexposed.config.web

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExposedTransactionPerRequestFilter(
    private val exposedDatabase: Database? = null
) : OncePerRequestFilter() {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        transaction(exposedDatabase) {
            log.debug { "Begin transaction" }
            filterChain.doFilter(request, response)
        }
    }

    private inline fun Logger.debug(lazyMessage: () -> String) {
        if (this.isDebugEnabled) this.debug(lazyMessage())
    }
}
