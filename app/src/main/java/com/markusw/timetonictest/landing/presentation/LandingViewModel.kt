package com.markusw.timetonictest.landing.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.timetonictest.auth.domain.AuthRepository
import com.markusw.timetonictest.core.presentation.UiText
import com.markusw.timetonictest.core.utils.Result
import com.markusw.timetonictest.landing.domain.use_cases.GetAllBooks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val getAllBooks: GetAllBooks,
    private val authRepository: AuthRepository
): ViewModel() {

    private val _uiState = MutableStateFlow(LandingState())
    val uiState = _uiState.asStateFlow()
    private val channel = Channel<LandingViewModelEvent>()
    val events = channel.receiveAsFlow()

    init {
        println("LandingViewModel init")
        _uiState.update {
            it.copy(isLoading = true)
        }

        viewModelScope.launch(Dispatchers.IO) {
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
                viewModelScope.launch(Dispatchers.IO) {
                    when (val result = authRepository.logout()) {
                        is Result.Error -> {
                            channel.send(LandingViewModelEvent.CloseSessionError(result.message ?: UiText.DynamicString("Unknown error")))
                        }
                        is Result.Success -> {
                            channel.send(LandingViewModelEvent.CloseSessionSuccess)
                        }
                    }
                }
            }
        }
    }

}