package com.github.antipovandrey.springexposed.config

import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transactionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class ExposedConfig(
    private val dataSource: DataSource
) {

    @Bean
    fun exposedDatabase(): Database {
        return Database.connect(dataSource)
    }

    @Bean
    fun exposedTransactionManager(): TransactionManager {
        return exposedDatabase().transactionManager
    }
}
