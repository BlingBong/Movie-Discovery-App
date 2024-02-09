package com.qifnav.moviediscovery.presentation.movie_listings

import com.qifnav.moviediscovery.domain.model.Movie

data class MovieListingsState(
    val movies: List<Movie>,
    val isLoadingMovies: Boolean
) {
    companion object {
        val defaultValue = MovieListingsState(
            movies = emptyList(),
            isLoadingMovies = false
        )
    }
}