package com.markusw.timetonictest.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.markusw.timetonictest.auth.domain.AuthRepository
import com.markusw.timetonictest.auth.domain.use_cases.ValidateEmail
import com.markusw.timetonictest.auth.domain.use_cases.ValidatePassword
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
    private val authRepository: AuthRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword
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
                val email = uiState.value.email
                val password = uiState.value.password
                val emailResult = validateEmail(email)
                val passwordResult = validatePassword(password)
                val isAnyError = listOf(
                    emailResult,
                    passwordResult
                ).any { !it.successful }

                if (isAnyError) {
                    _uiState.update {
                        it.copy(
                            emailError = emailResult.errorMessage,
                            passwordError = passwordResult.errorMessage
                        )
                    }
                    return
                }


                _uiState.update { it.copy(
                    isLoading = true,
                    emailError = null,
                    passwordError = null
                ) }

                viewModelScope.launch {
                    when (val result = authRepository.login(email, password)) {
                        is Result.Error -> {
                            channel.send(LoginViewModelEvent.LoginError(result.message ?: UiText.DynamicString("Unknown error")))
                        }
                        is Result.Success -> {
                            channel.send(LoginViewModelEvent.LoginSuccess)
                        }
                    }

                    _uiState.update {
                        it.copy(isLoading = false)
                    }
                }

            }
        }
    }

}