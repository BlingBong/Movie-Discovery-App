package com.qifnav.moviediscovery.domain.model

data class MovieDetail(
	val originalLanguage: String,
	val imdbId: String,
	val video: Boolean,
	val title: String,
	val backdropPath: String,
	val revenue: Int,
	val genres: List<DetailGenres>,
	val popularity: Double,
	val productionCountries: List<ProductionCountries>,
	val id: Int,
	val voteCount: Int,
	val budget: Int,
	val overview: String,
	val originalTitle: String,
	val runtime: Int,
	val posterPath: String,
	val spokenLanguages: List<SpokenLanguages>,
	val productionCompanies: List<ProductionCompanies>,
	val releaseDate: String,
	val voteAverage: Double,
	val belongsToCollection: String,
	val tagline: String,
	val adult: Boolean,
	val homepage: String,
	val status: String
)

data class SpokenLanguages(
	val name: String,
	val iso6391: String,
	val englishName: String
)

data class ProductionCountries(
	val iso31661: String,
	val name: String
)

data class ProductionCompanies(
	val logoPath: String,
	val name: String,
	val id: Int,
	val originCountry: String
)

data class DetailGenres(
	val name: String,
	val id: Int
)