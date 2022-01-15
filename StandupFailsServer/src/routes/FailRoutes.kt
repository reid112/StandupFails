package ca.rjreid.routes

import ca.rjreid.models.request.AddFailRequest
import ca.rjreid.models.response.AddFailResponse
import ca.rjreid.repository.Repository
import com.typesafe.config.ConfigFactory
import io.ktor.application.*
import io.ktor.application.application
import io.ktor.config.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.locations.post
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import java.time.LocalDate

import java.util.*

private const val FAIL = "v1/fail"
private val appConfig = HoconApplicationConfig(ConfigFactory.load())

@KtorExperimentalLocationsAPI
@Location(FAIL)
class GetFailsRoute

@KtorExperimentalLocationsAPI
@Location(FAIL)
class AddFailRoute

@KtorExperimentalLocationsAPI
fun Route.fails(db: Repository) {
    get<GetFailsRoute> {
        try {
            val fails = db.findFails()
            call.respond(fails)
        } catch (e: Throwable) {
            application.log.error("Failed to get Fails", e)
            call.respond(HttpStatusCode.BadRequest, "Problems getting Fails")
        }
    }
    post<AddFailRoute> {
        try {
            val request = call.receive<AddFailRequest>()
            val password = request.password

            if (password != appConfig.property("passwords.addFail").getString()) {
                throw Exception("Invalid Password")
            }

            val newFail = db.addFail(request.userId, LocalDate.now())

            newFail?.id?.let {
                val response = AddFailResponse(newFail.id)
                call.respond(response)
            }
        } catch (e: Throwable) {
            application.log.error("Failed to add fail", e)
            call.respond(HttpStatusCode.BadRequest, "Problems adding fail")
        }
    }
}