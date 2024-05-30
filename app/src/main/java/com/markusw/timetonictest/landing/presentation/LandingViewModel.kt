package com.markusw.timetonictest.landing.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(LandingState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update {
            it.copy(isLoading = true)
        }
    }

    fun onEvent(event: LandingEvent) {
        when (event) {
            is LandingEvent.CloseSession -> {}
        }
    }

}