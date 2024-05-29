package com.markusw.timetonictest.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.timetonictest.auth.domain.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: AuthEvent) {

        when (event) {
            is AuthEvent.ChangeEmail -> {
                _uiState.update { it.copy(email = event.email) }
            }
            is AuthEvent.ChangePassword -> {
                _uiState.update { it.copy(password = event.password) }
            }
            AuthEvent.Login -> {
                _uiState.update { it.copy(isLoading = true) }

                viewModelScope.launch {
                    // Login network request...
                }

            }
        }

    }


}