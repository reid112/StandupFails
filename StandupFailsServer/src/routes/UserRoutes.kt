package ca.rjreid.routes

import ca.rjreid.mappers.map
import ca.rjreid.repository.Repository
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import io.ktor.routing.*

private const val USERS = "v1/users"
private const val USER_WITH_MOST_FAILS = "$USERS/fails/top"

@KtorExperimentalLocationsAPI
@Location(USERS)
class UsersRoute

@KtorExperimentalLocationsAPI
@Location(USER_WITH_MOST_FAILS)
class UserWithMostFailsRoute

@KtorExperimentalLocationsAPI
fun Route.users(db: Repository) {
    get<UsersRoute> {
        try {
            val users = db.findUsers().map { it.map() }
            call.respond(users)
        } catch (e: Throwable) {
            application.log.error("Failed to get Users", e)
            call.respond(HttpStatusCode.BadRequest, "Problems getting Users")
        }
    }

    get<UserWithMostFailsRoute> {
        try {
            val user = db.findUserWithMostFails()

            if (user === null) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(user.map())
            }
        } catch (e: Throwable) {
            application.log.error("Failed to get User", e)
            call.respond(HttpStatusCode.BadRequest, "Problems getting User")
        }
    }
}