package com.github.antipovandrey.springexposed.data

import org.jetbrains.exposed.dao.IntIdTable

object PostTable : IntIdTable() {

    val title = varchar("name", 255)
    val content = text("content")
}
