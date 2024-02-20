package com.qifnav.moviediscovery.data.repository

import android.content.Context
import com.qifnav.moviediscovery.R
import com.qifnav.moviediscovery.data.api.MovieApi
import com.qifnav.moviediscovery.domain.model.Genre
import com.qifnav.moviediscovery.domain.model.Movie
import com.qifnav.moviediscovery.domain.model.MovieDetail
import com.qifnav.moviediscovery.domain.model.Review
import com.qifnav.moviediscovery.domain.model.Video
import com.qifnav.moviediscovery.domain.repository.MovieDiscoveryRepository
import com.qifnav.moviediscovery.util.Resource
import com.qifnav.moviediscovery.util.UIText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import logcat.asLog
import logcat.logcat
import retrofit2.HttpException
import java.io.IOException

class MovieDiscoveryRepositoryImpl(
    private val movieApi: MovieApi,
    private val appContext: Context
): MovieDiscoveryRepository {
    override fun getGenreListings(): Flow<Resource<List<Genre>>> = flow {
        emit(Resource.Loading())

        try {
            val response =
                movieApi.getGenreListings()

            when (response.genres) {
                null -> {
                    emit(Resource.Error(UIText.StringResource(R.string.em_unknown)))
                }
                else -> {
                    emit(Resource.Success(response.genres.map { it.toGenre() }))
                }
            }

        } catch (e: Exception) {
            logcat { e.asLog() }
            when (e) {
                is HttpException -> UIText.StringResource(R.string.em_unknown)

                is IOException -> UIText.StringResource(R.string.em_io_exception)

                else -> UIText.StringResource(R.string.em_unknown)

            }.let { emit(Resource.Error(it)) }
        }
    }

    override fun discoverMoviesByGenre(genreId: Int): Flow<Resource<List<Movie>>> = flow {
        emit(Resource.Loading())

        try {
            val response =
                movieApi.discoverMoviesByGenre(genreId = genreId)

            when (response.results) {
                null -> {
                    emit(Resource.Error(UIText.StringResource(R.string.em_unknown)))
                }
                else -> {
                    emit(Resource.Success(response.results.map { it.toMovie() }))
                }
            }

        } catch (e: Exception) {
            logcat { e.asLog() }
            when (e) {
                is HttpException -> UIText.StringResource(R.string.em_unknown)

                is IOException -> UIText.StringResource(R.string.em_io_exception)

                else -> UIText.StringResource(R.string.em_unknown)

            }.let { emit(Resource.Error(it)) }
        }
    }

    override fun getMovieDetail(movieId: Int): Flow<Resource<MovieDetail>> = flow {
        emit(Resource.Loading())

        try {
            val response =
                movieApi.getMovieDetail(movieId = movieId)

            when (response.title) {
                null -> {
                    emit(Resource.Error(UIText.StringResource(R.string.em_unknown)))
                }
                else -> {
                    emit(Resource.Success(response.toMovieDetail()))
                }
            }

        } catch (e: Exception) {
            logcat { e.asLog() }
            when (e) {
                is HttpException -> UIText.StringResource(R.string.em_unknown)

                is IOException -> UIText.StringResource(R.string.em_io_exception)

                else -> UIText.StringResource(R.string.em_unknown)

            }.let { emit(Resource.Error(it)) }
        }
    }

    override fun getMovieReviews(movieId: Int): Flow<Resource<List<Review>>> = flow {
        emit(Resource.Loading())

        try {
            val response =
                movieApi.getMovieReviews(movieId = movieId)

            when (response.results) {
                null -> {
                    emit(Resource.Error(UIText.StringResource(R.string.em_unknown)))
                }
                else -> {
                    emit(Resource.Success(response.results.map { it.toReview() }))
                }
            }

        } catch (e: Exception) {
            logcat { e.asLog() }
            when (e) {
                is HttpException -> UIText.StringResource(R.string.em_unknown)

                is IOException -> UIText.StringResource(R.string.em_io_exception)

                else -> UIText.StringResource(R.string.em_unknown)

            }.let { emit(Resource.Error(it)) }
        }
    }

    override fun getMovieVideos(movieId: Int): Flow<Resource<List<Video>>> = flow {
        emit(Resource.Loading())

        try {
            val response =
                movieApi.getMovieVideos(movieId = movieId)

            when (response.results) {
                null -> {
                    emit(Resource.Error(UIText.StringResource(R.string.em_unknown)))
                }
                else -> {
                    emit(Resource.Success(response.results.map { it.toVideo() }))
                }
            }

        } catch (e: Exception) {
            logcat { e.asLog() }
            when (e) {
                is HttpException -> UIText.StringResource(R.string.em_unknown)

                is IOException -> UIText.StringResource(R.string.em_io_exception)

                else -> UIText.StringResource(R.string.em_unknown)

            }.let { emit(Resource.Error(it)) }
        }
    }
}