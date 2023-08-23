package ca.rjreid.routes

import io.ktor.application.*
import io.ktor.locations.KtorExperimentalLocationsAPI
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.routing.application

private const val PING = "v1/ping"

@KtorExperimentalLocationsAPI
@Location(PING)
class PingRoute

@KtorExperimentalLocationsAPI
fun Route.ping() {
    get<PingRoute> {
        call.respond("${call.request.origin.remoteHost} | ${call.request.origin}")
    }
}
