package com.stewart.mikey.testme.core.data

import com.stewart.mikey.testme.BuildConfig
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json

@OptIn(ExperimentalSerializationApi::class)
val client = HttpClient(Android) {
    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                isLenient = true
                useAlternativeNames = true
                ignoreUnknownKeys = true
                explicitNulls = false
            }
        )
    }

    install(DefaultRequest) {
        header(HttpHeaders.ContentType, ContentType.Application.FormUrlEncoded)
        header(HttpHeaders.Authorization, "OAuth oauth_consumer_key=${BuildConfig.CONSUMER_KEY},oauth_signature_method=PLAINTEXT,oauth_signature=${BuildConfig.CONSUMER_SECRET}&")
    }

    engine {
        connectTimeout = DEFAULT_TIMEOUT
        socketTimeout = DEFAULT_TIMEOUT
    }
}

private const val DEFAULT_TIMEOUT = 100_000