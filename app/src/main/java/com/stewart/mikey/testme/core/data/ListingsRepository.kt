package com.stewart.mikey.testme.core.data

import io.ktor.client.call.body

class ListingsRepository(
    private val apiService: ApiService // TODO: replace with local/remote datasource intermediaries
) {
    suspend fun getLatestListings(pageSize: Int): LatestListingsResponse {
        return apiService.getLatestListings(pageSize).body()
    }
}