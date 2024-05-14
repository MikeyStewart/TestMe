package com.stewart.mikey.testme.core.domain

import com.stewart.mikey.testme.core.data.ListingsRepository

class GetLatestListingsUseCase(
    private val listingsRepository: ListingsRepository
) {
    suspend operator fun invoke(pageSize: Int = DEFAULT_PAGE_SIZE): List<Listing> {
        return listingsRepository.getLatestListings(pageSize).list.map { it.toDomain() }
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}