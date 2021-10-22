package ca.rjreid.models.response

import kotlinx.serialization.Serializable

@Serializable
data class GetFailsResponse(
    val username: String,
    val lastFailedDate: String,
    val failCount: Int
)