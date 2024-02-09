package com.qifnav.moviediscovery.presentation.movie_listings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qifnav.moviediscovery.domain.repository.MovieDiscoveryRepository
import com.qifnav.moviediscovery.presentation.genre_listings.GenreListingsState
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
class MovieListingsViewModel @Inject constructor(
    private val movieDiscoveryRepository: MovieDiscoveryRepository
): ViewModel() {
    private val _state = mutableStateOf(MovieListingsState.defaultValue)
    val state: State<MovieListingsState> = _state

    private val _eventFlow = Channel<UIEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    private var getMovieListingsJob: Job? = null

    fun getMovieListings(genreId: Int) {
        getMovieListingsJob?.cancel()
        getMovieListingsJob = viewModelScope.launch {
            movieDiscoveryRepository.discoverMoviesByGenre(genreId = genreId).onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(isLoadingMovies = false)
                        result.uiText?.let { _eventFlow.send(UIEvent.ShowSnackbar(it)) }
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            movies = result.data ?: emptyList(),
                            isLoadingMovies = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            movies = result.data ?: emptyList(),
                            isLoadingMovies = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}