package ca.rjreid.routes

import ca.rjreid.auth.JwtService
import ca.rjreid.auth.UserSession
import ca.rjreid.mappers.map
import ca.rjreid.models.request.RegisterRequest
import ca.rjreid.models.response.LoginResponse
import ca.rjreid.models.response.RegisterResponse
import ca.rjreid.repository.Repository
import io.ktor.application.application
import io.ktor.application.call
import io.ktor.application.log
import io.ktor.http.HttpStatusCode
import io.ktor.http.Parameters
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.request.receive
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.sessions.sessions
import io.ktor.sessions.set

private const val AUTH = "v1/auth"

private const val AUTH_REGISTER = "$AUTH/register"
private const val AUTH_LOGIN = "$AUTH/login"
private const val AUTH_LOGOUT = "$AUTH/logout"

private const val PARAM_DISPLAY_NAME = "displayName"
private const val PARAM_EMAIL = "email"
private const val PARAM_PASSWORD = "password"

@KtorExperimentalLocationsAPI
@Location(AUTH_REGISTER)
class AuthRegisterRoute

@KtorExperimentalLocationsAPI
@Location(AUTH_LOGIN)
class AuthLoginRoute

@KtorExperimentalLocationsAPI
@Location(AUTH_LOGOUT)
class AuthLogoutRoute

@KtorExperimentalLocationsAPI
fun Route.auth(db: Repository, jwtService: JwtService, hashFunction: (String) -> String) {
    post<AuthRegisterRoute> {
        try {
            val request = call.receive<RegisterRequest>()
            val hash = hashFunction(request.password)
            val newUser = db.addUser(request.email, request.displayName, hash)

            newUser?.userId?.let {
                call.sessions.set(UserSession(it))
                val response = RegisterResponse(jwtService.generateToken(newUser), newUser.map())
                call.respond(response)
            }
        } catch (e: Throwable) {
            application.log.error("Failed to register user", e)
            call.respond(HttpStatusCode.BadRequest, "Problems creating user")
        }
    }
    post<AuthLoginRoute> {
        val params = call.receive<Parameters>()
        val password = params[PARAM_PASSWORD] ?: return@post call.respond(HttpStatusCode.Unauthorized, "Missing Fields")
        val email = params[PARAM_EMAIL] ?: return@post call.respond(HttpStatusCode.Unauthorized, "Missing Fields")

        val hash = hashFunction(password)

        try {
            val currentUser = db.findUserByEmail(email)
            currentUser?.userId?.let {
                if (currentUser.passwordHash == hash) {
                    call.sessions.set(UserSession(it))
                    val response = LoginResponse(jwtService.generateToken(currentUser), currentUser.map())
                    call.respond(response)
                } else {
                    call.respond(HttpStatusCode.BadRequest, "Problems logging in user")
                }
            }
        } catch (e: Throwable) {
            application.log.error("Failed to log user in", e)
            call.respond(HttpStatusCode.BadRequest, "Problems logging in user")
        }
    }
    post<AuthLogoutRoute> {
        val params = call.receive<Parameters>()
        val email = params[PARAM_EMAIL] ?: return@post call.respond(HttpStatusCode.Unauthorized, "Missing Fields")

        try {
            val currentUser = db.findUserByEmail(email)
            currentUser?.userId?.let {
                call.sessions.clear(call.sessions.findName(UserSession::class))
                call.respond(HttpStatusCode.OK)
            }
        } catch (e: Throwable) {
            application.log.error("Failed to log user out", e)
            call.respond(HttpStatusCode.BadRequest, "Problems logging user out")
        }
    }
}
