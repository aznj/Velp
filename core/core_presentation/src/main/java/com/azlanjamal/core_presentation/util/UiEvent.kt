package com.azlanjamal.core_presentation.util

sealed class UiEvent {
    data class NavigateTo<T>(val data: T): UiEvent()
    object NavigateUp: UiEvent()
    data class ShowSnackbar(val message: UiText): UiEvent()
}
