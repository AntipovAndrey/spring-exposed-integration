package com.github.antipovandrey.springexposed.data

import org.jetbrains.exposed.dao.IntIdTable

object PostTable : IntIdTable() {

    val title = varchar("name", 255)
    val content = text("content")
}

object UserAccountTable : IntIdTable() {

    val login = varchar("login", 64)
}

object AccountApiTokenTable : IntIdTable() {

    val account = reference("user_account_id", UserAccountTable)
    val token = varchar("token", 64)
}
