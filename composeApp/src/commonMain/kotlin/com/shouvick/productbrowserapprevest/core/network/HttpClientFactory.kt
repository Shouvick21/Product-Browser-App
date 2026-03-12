package com.shouvick.productbrowserapprevest.core.network


import com.shouvick.productbrowserapprevest.core.logging.Log
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.statement.bodyAsText
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.uuid.ExperimentalUuidApi

object HttpClientFactory {
    @OptIn(ExperimentalUuidApi::class)
    fun create(
        engine: HttpClientEngine,
    ): HttpClient {
        val json =
            Json {
                ignoreUnknownKeys = true
                prettyPrint = true
                // it to include default values of a data model
                encodeDefaults = true
            }
        return HttpClient(engine) {
            install(ContentNegotiation) {
                json(
                    json = json,
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = 60_000 // whole request
                connectTimeoutMillis = 30_000 // TCP handshake
                socketTimeoutMillis = 60_000 // no data received
            }
            install(DefaultRequest) {
                url {
                    host = "dummyjson.com"
                    protocol = URLProtocol.HTTPS
                }

            }

            install(Logging) {
                logger =
                    object : Logger {
                        override fun log(message: String) {
                            Log.i("ApiCall", message)
                        }
                    }
            }
            install(
                createClientPlugin("CustomPlugin") {
                    onResponse { response ->
                        val text = response.bodyAsText()

                        if (text.startsWith("{")) {
                            val jsonElement = Json.parseToJsonElement(text)
                            println(
                                json.encodeToString(
                                    jsonElement,
                                ),
                            )
                        }
                    }
                },
            )
        }
    }
}
