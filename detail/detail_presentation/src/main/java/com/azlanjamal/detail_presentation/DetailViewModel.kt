package com.azlanjamal.detail_presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.azlanjamal.common.Resource
import com.azlanjamal.detail_domain.use_case.DetailUseCases
import com.azlanjamal.detail_presentation.common.Constant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _state = mutableStateOf(DetailState())
    val state: State<DetailState> = _state

    init {
        savedStateHandle.get<String>(Constant.PARAM_BUSINESS_ID)?.let { businessId ->
            getBusiness(businessId)
        }
    }

    private fun getBusiness(businessId: String) {
        detailUseCases.getBusinessById(businessId).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = DetailState(detail = result.data)
                }
                is Resource.Error -> {
                    _state.value = DetailState(
                        error = result.message ?: "An unexpected error occurred"
                    )
                }
                is Resource.Loading -> {
                    _state.value = DetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}