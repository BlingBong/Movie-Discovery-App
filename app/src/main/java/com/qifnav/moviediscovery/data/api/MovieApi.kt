package com.qifnav.moviediscovery.data.api

import com.qifnav.moviediscovery.data.model.GenreListDto
import com.qifnav.moviediscovery.data.model.MovieDetailDto
import com.qifnav.moviediscovery.data.model.MovieListDto
import com.qifnav.moviediscovery.data.model.ReviewListDto
import com.qifnav.moviediscovery.data.model.VideoListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("genre/movie/list")
    suspend fun getGenreListings(
        @Query("api_key") apiKey: String = API_KEY
    ): GenreListDto

    @GET("discover/movie")
    suspend fun discoverMoviesByGenre(
        @Query("with_genres") genreId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieListDto

    @GET("movie/{movie_id}")
    suspend fun getMovieDetail(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): MovieDetailDto

    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): ReviewListDto

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieVideos(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY
    ): VideoListDto

    companion object {
        const val API_KEY = "54ca01c7c39673395e1717ac9aef0f1f" // Replace with your actual API key
        const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}