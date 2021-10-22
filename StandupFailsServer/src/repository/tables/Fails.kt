package ca.rjreid.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Fails : Table() {
    val id : Column<Int> = integer("id").autoIncrement().primaryKey()
    val userId : Column<Int> = integer("user_id").references(Users.userId)
    val date = varchar("date", 128)
}