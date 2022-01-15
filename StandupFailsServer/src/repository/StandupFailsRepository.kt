package ca.rjreid.repository

import ca.rjreid.models.db.Fail
import ca.rjreid.models.db.User
import ca.rjreid.models.response.GetFailsResponse
import ca.rjreid.repository.DatabaseFactory.dbQuery
import ca.rjreid.repository.tables.Fails
import ca.rjreid.repository.tables.Users
import io.ktor.application.*
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.statements.InsertStatement
import java.util.*

class StandupFailsRepository: Repository {
    override suspend fun addUser(email: String, displayName: String, passwordHash: String) : User? {
        var statement : InsertStatement<Number>? = null
        dbQuery {
            statement = Users.insert { user ->
                user[Users.email] = email
                user[Users.displayName] = displayName
                user[Users.passwordHash] = passwordHash
            }
        }

        return rowToUser(statement?.resultedValues?.get(0))
    }

    override suspend fun findUsers(): List<User> {
        val list = mutableListOf<User>()
        dbQuery {
            Users.selectAll().forEach {
                rowToUser(it)?.let { list.add(it) }
            }
        }

        return list
    }

    override suspend fun findUser(userId: Int) = dbQuery {
        Users.select { Users.userId.eq(userId) }
            .map { rowToUser(it) }.singleOrNull()
    }

    override suspend fun findUserByEmail(email: String)= dbQuery {
        Users.select { Users.email.eq(email) }
            .map { rowToUser(it) }.singleOrNull()
    }

    override suspend fun findUserWithMostFails(): User? = dbQuery {
        (Fails innerJoin Users)
            .slice(Fails.id.count(), Fails.date.max(), Users.userId, Users.displayName, Users.email, Users.passwordHash)
            .selectAll()
            .groupBy(Users.userId)
            .orderBy(Fails.id.count(), order = SortOrder.DESC)
            .orderBy(Fails.date.max(), order = SortOrder.DESC)
            .limit(1)
            .map { rowToUser(it) }
            .singleOrNull()
    }

    override suspend fun addFail(userId: Int, date: String) : Fail? {
        var statement : InsertStatement<Number>? = null
        dbQuery {
            statement = Fails.insert { fail ->
                fail[Fails.userId] = userId
                fail[Fails.date] = date
            }
        }

        return rowToFail(statement?.resultedValues?.get(0))
    }

    override suspend fun findFails(): List<GetFailsResponse?> {
        val list = mutableListOf<GetFailsResponse?>()
        dbQuery {
            (Fails innerJoin Users)
                .slice(Fails.id.count(), Fails.date.max(), Users.displayName)
                .selectAll()
                .groupBy(Users.displayName)
                .orderBy(Fails.id.count(), order = SortOrder.DESC)
                .forEach {
                    val displayName = it[Users.displayName]
                    val count = it[Fails.id.count()]
                    val date = it[Fails.date.max()]

                    list.add(GetFailsResponse(displayName, date ?: "Unknown", count))
                }
        }

        return list
    }

    //region Helpers
    private fun rowToUser(row: ResultRow?): User? {
        if (row == null) {
            return null
        }

        return User(
            userId = row[Users.userId],
            email = row[Users.email],
            displayName = row[Users.displayName],
            passwordHash = row[Users.passwordHash]
        )
    }

    private fun rowToFail(row: ResultRow?): Fail? {
        if (row == null) {
            return null
        }

        return Fail(
            id = row[Fails.id],
            userId = row[Fails.userId],
            date = row[Fails.date]
        )
    }
    //endregion
}
