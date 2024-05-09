package com.stewart.mikey.testme.domain

// TODO: Update this class to better represent the data returned from the TM API
data class Listing(
    val imageUrl: String,
    val location: String,
    val title: String,
    val isClassified: Boolean,
    val priceDisplay: String,
    val buyNowPrice: String,
    val hasBuyNow: Boolean
)
