package ca.rjreid.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val userId : Column<Int> = integer("id").autoIncrement()
    val email = varchar("email", 128).uniqueIndex()
    val displayName = varchar("display_name", 256)
    val passwordHash = varchar("password_hash", 64)
    override val primaryKey = PrimaryKey(userId, name = "users_pkey")
}