package com.github.antipovandrey.springexposed.config.security

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter

@Configuration
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true
)
class SecurityConfig(
    @Value("\${sample.token-header-name}")
    private val headerName: String
) : WebSecurityConfigurerAdapter() {

    @Bean
    fun apiTokenFilter() = ApiAuthenticationTokenFilter(headerName)

    @Bean
    fun apiTokenService() = UserAccountSecurityService()

    @Bean
    fun apiTokenProvider() = ApiTokenAuthenticationProvider(apiTokenService())

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(apiTokenProvider())
    }

    override fun configure(http: HttpSecurity) {
        // todo: return 401 instead of 403
        http
                .addFilterBefore(apiTokenFilter(), BasicAuthenticationFilter::class.java)
                .authorizeRequests().anyRequest().permitAll()
                .and()
                .formLogin().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
    }
}
