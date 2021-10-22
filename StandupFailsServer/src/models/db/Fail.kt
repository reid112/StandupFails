package ca.rjreid.models.db

import io.ktor.auth.Principal
import kotlinx.serialization.Serializable

@Serializable
data class Fail(
    val id: Int,
    val userId: Int,
    val date: String
) : Principal