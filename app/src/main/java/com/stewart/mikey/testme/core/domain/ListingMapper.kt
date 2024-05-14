package com.stewart.mikey.testme.core.domain

import com.stewart.mikey.testme.core.data.ListingResponse
import java.text.DecimalFormat

internal fun ListingResponse.toDomain(): Listing {
    return Listing(
        listingId = listingId,
        title = title,
        buyNowPrice = formatPrice(buyNowPrice),
        pictureHref = pictureHref,
        region = region,
        hasBuyNow = hasBuyNow,
        isClassified = isClassified,
        priceDisplay = priceDisplay
    )
}

private fun formatPrice(price: Double?): String? {
    val priceFormat = DecimalFormat("$#,###.00")
    return try {
        priceFormat.format(price)
    } catch (e: Exception) {
        null
    }
}