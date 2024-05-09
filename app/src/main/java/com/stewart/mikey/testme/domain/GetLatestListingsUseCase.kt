package com.stewart.mikey.testme.domain

import kotlinx.coroutines.delay

class GetLatestListingsUseCase {
    suspend operator fun invoke(pageSize: Int = DEFAULT_PAGE_SIZE): List<Listing> {
        // Fake network call delay
        delay(2000)

        // TODO: Remove mock data and call API via repository
        return (1..pageSize).map {
            // Randomly generate variations of Listing data
            Listing(
                imageUrl = "https://randomfox.ca/images/${(1..100).random()}.jpg",
                location = "Wellington",
                title = "$it. ***NEW*** Randomly generated listing",
                isClassified = listOf(true, false).random(),
                priceDisplay = "${(1..999).random()}.00",
                buyNowPrice = "${(1..999).random()}.00",
                hasBuyNow = listOf(true, false).random()
            )
        }
    }

    companion object {
        const val DEFAULT_PAGE_SIZE = 20
    }
}