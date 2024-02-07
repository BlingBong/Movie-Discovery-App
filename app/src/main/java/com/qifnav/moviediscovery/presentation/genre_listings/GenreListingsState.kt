package com.qifnav.moviediscovery.presentation.genre_listings

import com.qifnav.moviediscovery.domain.model.Genre

data class GenreListingsState(
    val genres: List<Genre>,
    val isLoadingGenres: Boolean
) {
    companion object {
        val defaultValue = GenreListingsState(
            genres = emptyList(),
            isLoadingGenres = false
        )
    }
}