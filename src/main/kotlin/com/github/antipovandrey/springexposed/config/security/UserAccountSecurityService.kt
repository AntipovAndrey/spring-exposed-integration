package com.github.antipovandrey.springexposed.config.security

import com.github.antipovandrey.springexposed.data.AccountApiTokenTable
import com.github.antipovandrey.springexposed.data.UserAccountTable
import com.github.antipovandrey.springexposed.data.entity.UserAccount
import org.jetbrains.exposed.sql.select

class UserAccountSecurityService {

    fun findAccountByToken(token: String): UserAccount? {
        return AccountApiTokenTable
                .leftJoin(UserAccountTable)
                .select { AccountApiTokenTable.token eq token }
                .map { UserAccount.wrapRow(it) }
                .singleOrNull()
    }
}
