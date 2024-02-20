package com.qifnav.moviediscovery.data.model

import com.google.gson.annotations.SerializedName
import com.qifnav.moviediscovery.domain.model.DetailGenres
import com.qifnav.moviediscovery.domain.model.MovieDetail
import com.qifnav.moviediscovery.domain.model.ProductionCompanies
import com.qifnav.moviediscovery.domain.model.ProductionCountries
import com.qifnav.moviediscovery.domain.model.SpokenLanguages

data class MovieDetailDto(
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("imdb_id") val imdbId: String?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("title") val title: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("revenue") val revenue: Int?,
    @SerializedName("genres") val genres: List<DetailGenresItem>?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("production_countries") val productionCountries: List<ProductionCountriesItem>?,
    @SerializedName("id") val id: Int?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("budget") val budget: Int?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("runtime") val runtime: Int?,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("spoken_languages") val spokenLanguages: List<SpokenLanguagesItem>?,
    @SerializedName("production_companies") val productionCompanies: List<ProductionCompaniesItem>?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("vote_average") val voteAverage: Double?,
    @SerializedName("belongs_to_collection") val belongsToCollection: String?,
    @SerializedName("tagline") val tagline: String?,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("homepage") val homepage: String?,
    @SerializedName("status") val status: String?
) {
    fun toMovieDetail() = MovieDetail(
        originalLanguage = this.originalLanguage ?: "",
        imdbId = this.imdbId ?: "",
        video = this.video ?: false,
        title = this.title ?: "",
        backdropPath = this.backdropPath ?: "",
        revenue = this.revenue ?: 0,
        genres = this.genres?.map { it.toDetailGenres() } ?: emptyList(),
        popularity = this.popularity ?: 0.0,
        productionCountries = this.productionCountries?.map { it.toProductionCountries() } ?: emptyList(),
        id = this.id ?: 0,
        voteCount = this.voteCount ?: 0,
        budget = this.budget ?: 0,
        overview = this.overview ?: "",
        originalTitle = this.originalTitle ?: "",
        runtime = this.runtime ?: 0,
        posterPath = this.posterPath ?: "",
        spokenLanguages = this.spokenLanguages?.map { it.toSpokenLanguages() } ?: emptyList(),
        productionCompanies = this.productionCompanies?.map { it.toProductionCompanies() } ?: emptyList(),
        releaseDate = this.releaseDate ?: "",
        voteAverage = this.voteAverage ?: 0.0,
        belongsToCollection = this.belongsToCollection ?: "",
        tagline = this.tagline ?: "",
        adult = this.adult ?: false,
        homepage = this.homepage ?: "",
        status = this.status ?: ""
    )
}

data class SpokenLanguagesItem(
    @SerializedName("name") val name: String?,
    @SerializedName("iso_639_1") val iso6391: String?,
    @SerializedName("english_name") val englishName: String?
) {
    fun toSpokenLanguages() = SpokenLanguages(
        name = this.name ?: "",
        iso6391 = this.iso6391 ?: "",
        englishName = this.englishName ?: ""
    )
}

data class ProductionCountriesItem(
    @SerializedName("iso_3166_1") val iso31661: String?,
    @SerializedName("name") val name: String?
) {
    fun toProductionCountries() = ProductionCountries(
        iso31661 = this.iso31661 ?: "",
        name = this.name ?: ""
    )
}

data class ProductionCompaniesItem(
    @SerializedName("logo_path") val logoPath: String?,
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("origin_country") val originCountry: String?
) {
    fun toProductionCompanies() = ProductionCompanies(
        logoPath = this.logoPath ?: "",
        name = this.name ?: "",
        id = this.id ?: 0,
        originCountry = this.originCountry ?: ""
    )
}

data class DetailGenresItem(
    @SerializedName("name") val name: String?,
    @SerializedName("id") val id: Int?
) {
    fun toDetailGenres() = DetailGenres(
        name = this.name ?: "",
        id = this.id ?: 0
    )
}
