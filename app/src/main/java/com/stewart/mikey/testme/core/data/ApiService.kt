package com.stewart.mikey.testme.core.data

import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(private val client: HttpClient) {
    suspend fun getLatestListings(pageSize: Int) =
        client.get(BASE_URL + "listings/latest.json") {
            parameter("rows", pageSize)
            parameter("photo_size", "List")
        }

    companion object {
        private const val BASE_URL = "https://api.tmsandbox.co.nz/v1/"
    }
}