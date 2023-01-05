package com.azlanjamal.search_presentation

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azlanjamal.core_presentation.util.UiEvent
import com.azlanjamal.core_presentation.util.UiText
import com.azlanjamal.search_domain.use_case.SearchUseCases
import com.azlanjamal.common.R
import com.azlanjamal.location_domain.use_case.LocationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCases: SearchUseCases,
    private val locationUseCases: LocationUseCases
): ViewModel() {

    var state by mutableStateOf(SearchState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    private var getLatestLatLngJob: Job? = null

    init {
        getLatestLatLng()
    }

    fun onEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }
            is SearchEvent.OnSearch -> {
                executeSearch()
            }
            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }
            is SearchEvent.OnBusinessClick -> {
                openBusinessDetail(event)
            }
        }
    }

    private fun getLatestLatLng() {
        getLatestLatLngJob?.cancel()
        getLatestLatLngJob = locationUseCases
            .getLatestLatLng().onEach {
                state = state.copy(
                    latitude = it.latitude,
                    longitude = it.longitude
                )
            }
            .launchIn(viewModelScope)
    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true,
                businesses = emptyList()
            )
            searchUseCases
                .searchBusiness(
                    state.query,
                    latitude = state.latitude,
                    longitude = state.longitude
                )
                .onSuccess { businesses ->
                    state = state.copy(
                        businesses = businesses,
                        isSearching = false,
                        query = ""
                    )
                }
                .onFailure {
                    state = state.copy(isSearching = false)
                    _uiEvent.send(
                        UiEvent.ShowSnackbar(
                            UiText.StringResource(R.string.error_something_went_wrong)
                        )
                    )
                }
        }
    }

    private fun openBusinessDetail(event: SearchEvent.OnBusinessClick) {
        viewModelScope.launch {
            _uiEvent.send(UiEvent.NavigateTo(event.business.id))
        }
    }
}