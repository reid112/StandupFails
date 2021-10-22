package ca.rjreid.models.response

import kotlinx.serialization.Serializable

@Serializable
data class FailResponse(
    val id: Int,
    val userId: Int,
    val date: String
)