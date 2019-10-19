package com.github.antipovandrey.springexposed.data.entity

import com.github.antipovandrey.springexposed.data.PostTable
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class Post(id: EntityID<Int>) : IntEntity(id) {

    var title by PostTable.title
    var content by PostTable.content

    companion object : IntEntityClass<Post>(PostTable)
}
