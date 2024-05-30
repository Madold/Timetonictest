package com.markusw.timetonictest.auth.presentation

import com.markusw.timetonictest.core.presentation.UiText

/**
 * Represents the state of the login screen.
 *
 * @param email The email entered by the user.
 * @param password The password entered by the user.
 * @param isLoading Whether the login process is currently loading.
 * @param emailError The error message to display for the email field, if any.
 * @param passwordError The error message to display for the password field, if any.
 */
data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val emailError: UiText? = null,
    val passwordError: UiText? = null,
)
