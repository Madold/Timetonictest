package com.markusw.timetonictest.auth.presentation

import com.markusw.timetonictest.core.presentation.UiText

sealed interface LoginViewModelEvent {

    data object LoginSuccess : LoginViewModelEvent

    data class LoginError(val message: UiText) : LoginViewModelEvent

}