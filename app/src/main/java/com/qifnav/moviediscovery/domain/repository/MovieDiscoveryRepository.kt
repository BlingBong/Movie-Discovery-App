package com.qifnav.moviediscovery.domain.repository

import com.qifnav.moviediscovery.domain.model.Genre
import com.qifnav.moviediscovery.domain.model.Movie
import com.qifnav.moviediscovery.domain.model.Review
import com.qifnav.moviediscovery.domain.model.Video
import com.qifnav.moviediscovery.util.Resource
import kotlinx.coroutines.flow.Flow

interface MovieDiscoveryRepository {
    fun getGenreListings(): Flow<Resource<List<Genre>>>
    fun discoverMoviesByGenre(genreId: Int): Flow<Resource<List<Movie>>>
    fun getMovieReviews(movieId: Int): Flow<Resource<List<Review>>>
    fun getMovieVideos(movieId: Int): Flow<Resource<List<Video>>>
}