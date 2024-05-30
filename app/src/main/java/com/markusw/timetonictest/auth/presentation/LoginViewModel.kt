package com.markusw.timetonictest.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.timetonictest.auth.domain.AuthRepository
import com.markusw.timetonictest.core.presentation.UiText
import com.markusw.timetonictest.core.utils.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginState())
    val uiState = _uiState.asStateFlow()
    private val channel = Channel<LoginViewModelEvent>()
    val events = channel.receiveAsFlow()

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
                    val email = uiState.value.email
                    val password = uiState.value.password
                    when (val result = authRepository.login(email, password)) {
                        is Result.Error -> {
                            channel.send(LoginViewModelEvent.LoginError(result.message ?: UiText.DynamicString("Unknown error")))
                        }
                        is Result.Success -> {
                            channel.send(LoginViewModelEvent.LoginSuccess)
                        }
                    }
                }

            }
        }

    }


}