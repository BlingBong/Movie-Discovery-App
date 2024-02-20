package com.qifnav.moviediscovery.presentation.movie_detail

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qifnav.moviediscovery.domain.repository.MovieDiscoveryRepository
import com.qifnav.moviediscovery.presentation.util.UIEvent
import com.qifnav.moviediscovery.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDiscoveryRepository: MovieDiscoveryRepository
): ViewModel() {
    private val _state = mutableStateOf(MovieDetailState.defaultValue)
    val state: State<MovieDetailState> = _state

    private val _eventFlow = Channel<UIEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private var getMovieDetailJob: Job? = null

    fun getMovieDetail(movieId: Int) {
        getMovieDetailJob?.cancel()
        getMovieDetailJob = viewModelScope.launch {
            movieDiscoveryRepository.getMovieDetail(movieId = movieId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(isLoadingReview = false)
                        result.uiText?.let { _eventFlow.send(UIEvent.ShowSnackbar(it)) }
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            movieDetail = result.data ?: state.value.movieDetail,
                            isLoadingDescription = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            movieDetail = result.data ?: state.value.movieDetail,
                            isLoadingDescription = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    private var getMovieReviewsJob: Job? = null

    fun getMovieReviews(movieId: Int) {
        getMovieReviewsJob?.cancel()
        getMovieReviewsJob = viewModelScope.launch {
            movieDiscoveryRepository.getMovieReviews(movieId = movieId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(isLoadingReview = false)
                        result.uiText?.let { _eventFlow.send(UIEvent.ShowSnackbar(it)) }
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            reviews = result.data ?: emptyList(),
                            isLoadingReview = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            reviews = result.data ?: emptyList(),
                            isLoadingReview = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }

    private var getMovieVideosJob: Job? = null

    fun getMovieVideos(movieId: Int) {
        getMovieVideosJob?.cancel()
        getMovieVideosJob = viewModelScope.launch {
            movieDiscoveryRepository.getMovieVideos(movieId = movieId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(isLoadingVideo = false)
                        result.uiText?.let { _eventFlow.send(UIEvent.ShowSnackbar(it)) }
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            videos = result.data ?: emptyList(),
                            isLoadingVideo = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            videos = result.data ?: emptyList(),
                            isLoadingVideo = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}