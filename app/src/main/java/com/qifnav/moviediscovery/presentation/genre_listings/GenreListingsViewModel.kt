package com.qifnav.moviediscovery.presentation.genre_listings

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.qifnav.moviediscovery.domain.model.Genre
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
class GenreListingsViewModel  @Inject constructor(
    private val movieDiscoveryRepository: MovieDiscoveryRepository
): ViewModel() {
    private val _state = mutableStateOf(GenreListingsState.defaultValue)
    val state: State<GenreListingsState> = _state

    private val _eventFlow = Channel<UIEvent>()
    val eventFlow = _eventFlow.receiveAsFlow()

    init {
        getGenreListings()
    }

    private var getGenreListingsJob: Job? = null
    fun getGenreListings() {
        getGenreListingsJob?.cancel()
        getGenreListingsJob = viewModelScope.launch {
            movieDiscoveryRepository.getGenreListings().onEach { result ->
                when (result) {
                    is Resource.Error -> {
                        _state.value = state.value.copy(isLoadingGenres = false)
                        result.uiText?.let { _eventFlow.send(UIEvent.ShowSnackbar(it)) }
                    }
                    is Resource.Loading -> {
                        _state.value = state.value.copy(
                            genres = result.data ?: emptyList(),
                            isLoadingGenres = true
                        )
                    }
                    is Resource.Success -> {
                        _state.value = state.value.copy(
                            genres = result.data ?: emptyList(),
                            isLoadingGenres = false
                        )
                    }
                }
            }.launchIn(this)
        }
    }
}