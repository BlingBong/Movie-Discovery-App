package com.qifnav.moviediscovery.data.model

import com.google.gson.annotations.SerializedName
import com.qifnav.moviediscovery.domain.model.Genre

data class GenreListDto(
    @SerializedName("genres") val genres: List<GenresItem>?
)

data class GenresItem(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?
) {
    fun toGenre() = Genre(
        id = this.id ?: 0,
        name = this.name ?: ""
    )
}