package com.markusw.timetonictest.auth.presentation

import com.markusw.timetonictest.core.presentation.UiText

/**
 * Represents an event that can be emitted by the [LoginViewModel].
 */
sealed interface LoginViewModelEvent {

    data object LoginSuccess : LoginViewModelEvent

    data class LoginError(val message: UiText) : LoginViewModelEvent

}