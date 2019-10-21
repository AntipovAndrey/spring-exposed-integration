package com.github.antipovandrey.springexposed.config.web

import org.jetbrains.exposed.sql.Database
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig(
    private val exposedDatabase: Database
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        // MVC interceptor-based implementation won't work if a servlet filter needed in a transaction
        // registry.addInterceptor(ExposedTransactionPerRequestInterceptor(exposedTxManager))
    }

    @Bean
    fun registerOpenEntityManagerInViewFilterBean(): FilterRegistrationBean<*> {
        return FilterRegistrationBean<ExposedTransactionPerRequestFilter>().apply {
            this.filter = ExposedTransactionPerRequestFilter(exposedDatabase)
            this.order = 4 // look application.properties - security's filter has 5 order
        }
    }
}
