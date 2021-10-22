package ca.rjreid.repository

import ca.rjreid.models.db.Fail
import ca.rjreid.models.db.User
import ca.rjreid.models.response.FailResponse
import ca.rjreid.models.response.GetFailsResponse
import ca.rjreid.models.response.UserResponse

interface Repository {
    suspend fun addUser(email: String, displayName: String, passwordHash: String): User?
    suspend fun findUsers(): List<User>
    suspend fun findUser(userId: Int): User?
    suspend fun findUserByEmail(email: String): User?
    suspend fun findUserWithMostFails(): User?
    suspend fun addFail(userId: Int, date: String): Fail?
    suspend fun findFails(): List<GetFailsResponse?> // TODO: Refactor this to not use GetFailsResponse
}