package ca.rjreid.models.request

import kotlinx.serialization.Serializable

@Serializable
data class AddFailRequest(val userId: Int)