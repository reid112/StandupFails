package ca.rjreid.models.db

import io.ktor.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: Int,
    val email: String,
    val displayName: String,
    val passwordHash: String
) : Principal
