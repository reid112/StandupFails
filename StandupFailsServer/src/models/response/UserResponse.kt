package ca.rjreid.models.response

import ca.rjreid.models.db.User
import kotlinx.serialization.Serializable

@Serializable
data class UserResponse(
    val id: Int,
    val displayName: String,
    val email: String,
)