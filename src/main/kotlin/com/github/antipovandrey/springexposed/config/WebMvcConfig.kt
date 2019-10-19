package com.github.antipovandrey.springexposed.config

import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(
    private val exposedTxManager: TransactionManager
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(ExposedTransactionPerRequestInterceptor(exposedTxManager))
    }
}