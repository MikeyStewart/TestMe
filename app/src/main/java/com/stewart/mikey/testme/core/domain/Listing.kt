package com.stewart.mikey.testme.core.domain

data class Listing(
    val listingId: Long,
    val title: String,
    val buyNowPrice: String?,
    val pictureHref: String?,
    val region: String,
    val hasBuyNow: Boolean = false,
    val isClassified: Boolean = false,
    val priceDisplay: String,
)
