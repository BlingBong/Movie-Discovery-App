package com.qifnav.moviediscovery.data.model

import com.google.gson.annotations.SerializedName
import com.qifnav.moviediscovery.domain.model.Video

data class VideoListDto(
    @SerializedName("id") val id: Int?,
    @SerializedName("results") val results: List<VideosItem>?
)

data class VideosItem(
    @SerializedName("id") val id: String?,
    @SerializedName("iso_639_1") val iso6391: String?,
    @SerializedName("iso_3166_1") val iso31661: String?,
    @SerializedName("key") val key: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("site") val site: String?,
    @SerializedName("size") val size: Int?,
    @SerializedName("type") val type: String?
) {
    fun toVideo() = Video(
        id = this.id ?: "",
        iso6391 = this.iso6391 ?: "",
        iso31661 = this.iso31661 ?: "",
        key = this.key ?: "",
        name = this.name ?: "",
        site = this.site ?: "",
        size = this.size ?: 0,
        type = this.type ?: ""
    )
}
