package ca.rjreid.repository.tables

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.date
import java.time.LocalDate
import java.util.*

object Fails : Table() {
    val id : Column<Int> = integer("id").autoIncrement()
    val userId : Column<Int> = integer("user_id").references(Users.userId)
    val date : Column<LocalDate> = date("date")
    override val primaryKey = PrimaryKey(id, name = "fails_pkey")
}