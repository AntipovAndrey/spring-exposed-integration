package com.github.antipovandrey.springexposed.config

import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class ExposedTransactionPerRequestInterceptor(
    private val exposedTransactionManager: TransactionManager
) : HandlerInterceptor {

    private val log: Logger = LoggerFactory.getLogger(javaClass)

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        log.debug { "Begin transaction" }
        exposedTransactionManager.newTransaction()
        return true
    }

    override fun postHandle(
        request: HttpServletRequest,
        response: HttpServletResponse,
        handler: Any,
        modelAndView: ModelAndView?
    ) = Unit

    override fun afterCompletion(request: HttpServletRequest, response: HttpServletResponse, handler: Any, ex: Exception?) {
        val tx = exposedTransactionManager.currentOrNull() ?: error("No transaction found")

        if (ex == null) {
            log.debug { "Commit transaction" }
            tx.commit()
        } else {
            log.debug { "Rollback transaction due to $ex" }
            tx.rollback()
        }
    }

    private inline fun Logger.debug(lazyMessage: () -> String) {
        if (this.isDebugEnabled) this.debug(lazyMessage())
    }
}
