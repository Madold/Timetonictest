package com.markusw.timetonictest.auth.presentation

import com.markusw.timetonictest.core.presentation.UiText

data class LoginState(
    val email: String = "android.developer@timetonic.com",
    val password: String = "Android.developer1",
    val isLoading: Boolean = false,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
)
