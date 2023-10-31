package dev.eric.binaria.network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.plugin
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class StockWaveHttpClientImpl : StockWaveHttpClient {

    private val client = HttpClient {
        defaultRequest {
            url(StockWaveHttpClient.BASE_URL)
            contentType(ContentType.Application.Json)
        }

        install(UserAgent) {
            agent = "Binaria"
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 60000
            socketTimeoutMillis = 60000
            connectTimeoutMillis = 6000
        }

        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }

        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
    }

    override fun getHttpClient(appKey: String, symbols: String): HttpClient {
        client.plugin(HttpSend).intercept { request ->
            request.url.parameters.append("app_id", appKey)
            if(symbols.isNotEmpty()) {
                request.url.parameters.append("symbols", symbols)
            }
            execute(request)
        }

        return client
    }
}

interface StockWaveHttpClient {
    fun getHttpClient(appKey: String, symbols: String = ""): HttpClient

    companion object {
        const val BASE_URL = "http://openexchangerates.org"
    }
}