package com.markusw.timetonictest.landing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.timetonictest.landing.domain.use_cases.GetAllBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val getAllBooks: GetAllBooks
): ViewModel() {

    private val _uiState = MutableStateFlow(LandingState())
    val uiState = _uiState.asStateFlow()

    init {
        println("LandingViewModel init")
        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch {
            val books = getAllBooks()
            println("books: $books")
            _uiState.update {
                it.copy(
                    isLoading = false,
                    books = books
                )
            }
        }
    }

    fun onEvent(event: LandingEvent) {
        when (event) {
            is LandingEvent.CloseSession -> {

            }
        }
    }

}