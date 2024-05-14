package com.stewart.mikey.testme.core.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LatestListingsResponse(
    @SerialName("TotalCount")
    val totalCount: Int,
    @SerialName("Page")
    val page: Int,
    @SerialName("PageSize")
    val pageSize: Int,
    @SerialName("List")
    val list: List<ListingResponse>,
)