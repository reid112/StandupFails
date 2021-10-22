package ca.rjreid.mappers

import ca.rjreid.models.db.User
import ca.rjreid.models.response.UserResponse

fun User.map() = UserResponse(
    id = userId,
    displayName = displayName,
    email = email
)