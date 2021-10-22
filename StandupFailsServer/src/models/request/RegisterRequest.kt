package ca.rjreid.models.request

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(val displayName: String, val email: String, val password: String)