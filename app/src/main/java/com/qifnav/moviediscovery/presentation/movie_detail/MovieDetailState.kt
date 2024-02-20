package com.qifnav.moviediscovery.presentation.movie_detail

import com.qifnav.moviediscovery.domain.model.Movie
import com.qifnav.moviediscovery.domain.model.MovieDetail
import com.qifnav.moviediscovery.domain.model.Review
import com.qifnav.moviediscovery.domain.model.Video

data class MovieDetailState(
    val movieDetail: MovieDetail,
    val reviews: List<Review>,
    val videos: List<Video>,
    val isLoadingDescription: Boolean,
    val isLoadingReview: Boolean,
    val isLoadingVideo: Boolean
) {
    companion object {
        val defaultValue = MovieDetailState(
            movieDetail = MovieDetail(
                originalLanguage = "",
                imdbId = "",
                video = false,
                title = "",
                backdropPath = "",
                revenue = 0,
                genres = emptyList(),
                popularity = 0.0,
                productionCountries = emptyList(),
                id = 0,
                voteCount = 0,
                budget = 0,
                overview = "",
                originalTitle = "",
                runtime = 0,
                posterPath = "",
                spokenLanguages = emptyList(),
                productionCompanies = emptyList(),
                releaseDate = "",
                voteAverage = 0.0,
                belongsToCollection = "",
                tagline = "",
                adult = false,
                homepage = "",
                status = ""
            ),
            reviews = emptyList(),
            videos = emptyList(),
            isLoadingDescription = false,
            isLoadingReview = false,
            isLoadingVideo = false
        )
    }
}