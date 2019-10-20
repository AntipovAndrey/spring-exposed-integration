package com.github.antipovandrey.springexposed.data.entity

import com.github.antipovandrey.springexposed.data.UserAccountTable
import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass

class UserAccount(id: EntityID<Int>) : IntEntity(id) {

    var login by UserAccountTable.login

    companion object : IntEntityClass<UserAccount>(UserAccountTable)
}
