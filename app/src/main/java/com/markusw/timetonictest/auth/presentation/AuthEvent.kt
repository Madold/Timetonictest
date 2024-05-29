package com.markusw.timetonictest.auth.presentation

sealed interface AuthEvent {

    data class ChangeEmail(val email: String) : AuthEvent
    data class ChangePassword(val password: String) : AuthEvent
    data object Login : AuthEvent

}