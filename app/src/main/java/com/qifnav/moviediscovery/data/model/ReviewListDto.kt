package com.qifnav.moviediscovery.data.model

import com.google.gson.annotations.SerializedName
import com.qifnav.moviediscovery.domain.model.Review

data class ReviewListDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("page") val page: Int?,
    @SerializedName("results") val results: List<ReviewsItem>?,
    @SerializedName("total_pages") val totalPages: Int?,
    @SerializedName("total_results") val totalResults: Int?
)

data class ReviewsItem(
    @SerializedName("author") val author: String?,
    @SerializedName("content") val content: String?,
    @SerializedName("id") val id: String?,
    @SerializedName("url") val url: String?
) {
    fun toReview() = Review(
        author = this.author ?: "",
        content = this.content ?: "",
        id = this.id ?: "",
        url = this.url ?: ""
    )
}