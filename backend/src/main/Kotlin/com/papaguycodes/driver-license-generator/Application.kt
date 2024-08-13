<!-- //💡In the Beginning, PaPaGuy wrote beautiful Codes < /> 💜❤️ // -->
package com.papaguycodes.driver-license-generator

import io.ktor.application.*
import io.ktor.features.ContentNegotiation
import io.ktor.features.StatusPages
import io.ktor.http.HttpStatusCode
import io.ktor.jackson.jackson
import io.ktor.response.respond
import io.ktor.routing.*
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.request.receive
import io.ktor.server.html.*
import kotlinx.html.*
import com.github.javafaker.Faker

fun main() {
    embeddedServer(Netty, port = 8080, module = Application::module).start(wait = true)
}

fun Application.module() {
    install(ContentNegotiation) {
        jackson { }
    }

    install(StatusPages) {
        exception<Throwable> { cause ->
            call.respond(HttpStatusCode.InternalServerError, cause.localizedMessage)
        }
    }

    routing {
        route("/generate") {
            post {
                val params = call.receive<LicenseRequest>()
                val license = generateLicense(params.state, params.firstName, params.lastName)
                call.respond(LicenseResponse(license))
            }
        }

        route("/validate") {
            post {
                val params = call.receive<ValidationRequest>()
                val isValid = validateLicenseNumber(params.state, params.licenseNumber)
                call.respond(mapOf("valid" to isValid))
            }
        }
    }
}

data class LicenseRequest(val state: String, val firstName: String, val lastName: String)
data class LicenseResponse(val licenseNumber: String)
data class ValidationRequest(val state: String, val licenseNumber: String)

fun generateLicense(state: String, firstName: String, lastName: String): String {
    val initials = (firstName.firstOrNull() ?: 'X').toUpperCase() + (lastName.firstOrNull() ?: 'X').toUpperCase()
    val randomDigits = (100000..999999).random().toString()
    return "$state-$initials-$randomDigits"
}

fun validateLicenseNumber(state: String, licenseNumber: String): Boolean {
    val patterns = mapOf(
        'AL': /^AL[A-Z]{3}\d{2}\d{6}$/,
            'AK': /^AK[A-Z]{5}\d{4}$/,
            'AZ': /^AZ[A-Z]\d{2}\d{6}$/,
            'AR': /^AR[A-Z]{4}\d{2}\d{6}$/,
            'CA': /^CA[A-Z]{5}\d{2}\d{6}$/,
            'CO': /^CO[A-Z]{3}\d{4}\d{6}$/,
            'CT': /^CT[A-Z]{3}\d{2}\d{6}$/,
            'DE': /^DE[A-Z]{3}\d{4}\d{6}$/,
            'FL': /^FL[A-Z]{5}\d{2}\d{6}$/,
            'GA': /^GA[A-Z]{6}\d{4}\d{6}$/,
            'HI': /^HI[A-Z]{3}\d{2}\d{6}$/,
            'ID': /^ID[A-Z]{4}\d{4}\d{6}$/,
            'IL': /^IL[A-Z]{5}\d{4}\d{6}$/,
            'IN': /^IN[A-Z]{4}\d{2}\d{6}$/,
            'IA': /^IA[A-Z]{3}\d{4}\d{6}$/,
            'KS': /^KS[A-Z]{5}\d{4}\d{6}$/,
            'KY': /^KY[A-Z]{4}\d{4}\d{6}$/,
            'LA': /^LA[A-Z]{4}\d{2}\d{6}$/,
            'ME': /^ME[A-Z]{4}\d{4}\d{6}$/,
            'MD': /^MD[A-Z]{4}\d{2}\d{6}$/,
            'MA': /^MA[A-Z]{3}\d{4}\d{6}$/,
            'MI': /^MI[A-Z]{5}\d{2}\d{6}$/,
            'MN': /^MN[A-Z]{4}\d{4}\d{6}$/,
            'MS': /^MS[A-Z]{4}\d{4}\d{6}$/,
            'MO': /^MO[A-Z]{5}\d{2}\d{6}$/,
            'MT': /^MT[A-Z]{4}\d{4}\d{6}$/,
            'NE': /^NE[A-Z]{4}\d{2}\d{6}$/,
            'NV': /^NV[A-Z]{4}\d{4}\d{6}$/,
            'NH': /^NH[A-Z]{4}\d{2}\d{6}$/,
            'NJ': /^NJ[A-Z]{5}\d{4}\d{6}$/,
            'NM': /^NM[A-Z]{4}\d{4}\d{6}$/,
            'NY': /^NY[A-Z]{3}\d{2}\d{6}$/,
            'NC': /^NC[A-Z]{4}\d{2}\d{6}$/,
            'ND': /^ND[A-Z]{4}\d{4}\d{6}$/,
            'OH': /^OH[A-Z]{4}\d{2}\d{6}$/,
            'OK': /^OK[A-Z]{4}\d{4}\d{6}$/,
            'OR': /^OR[A-Z]{4}\d{2}\d{6}$/,
            'PA': /^PA[A-Z]{4}\d{4}\d{6}$/,
            'RI': /^RI[A-Z]{3}\d{4}\d{6}$/,
            'SC': /^SC[A-Z]{5}\d{2}\d{6}$/,
            'SD': /^SD[A-Z]{4}\d{4}\d{6}$/,
            'TN': /^TN[A-Z]{4}\d{2}\d{6}$/,
            'TX': /^TX[A-Z]{5}\d{2}\d{6}$/,
            'UT': /^UT[A-Z]{4}\d{4}\d{6}$/,
            'VT': /^VT[A-Z]{4}\d{2}\d{6}$/,
            'VA': /^VA[A-Z]{5}\d{4}\d{6}$/,
            'WA': /^WA[A-Z]{4}\d{4}\d{6}$/,
            'WV': /^WV[A-Z]{4}\d{2}\d{6}$/,
            'WI': /^WI[A-Z]{5}\d{4}\d{6}$/,
            'WY': /^WY[A-Z]{4}\d{2}\d{6}$/,
            'DC': /^DC[A-Z]{4}\d{2}\d{6}$/
    )
    val regex = patterns[state]?.toRegex() ?: return false
    return regex.matches(licenseNumber)
}
