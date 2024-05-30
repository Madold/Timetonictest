package com.markusw.timetonictest.auth.presentation

import com.markusw.timetonictest.core.presentation.UiText

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
)
