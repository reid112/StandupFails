package ca.rjreid.models.response

import kotlinx.serialization.Serializable

@Serializable
data class RegisterResponse(
    val token: String,
    val user: UserResponse
)