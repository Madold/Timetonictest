package com.markusw.timetonictest.auth.presentation

/**
 * Sealed class representing events that can be triggered by the user in the auth screen.
 * Each event can carry additional data.
 * @see AuthViewModel
 */
sealed interface AuthEvent {

    data class ChangeEmail(val email: String) : AuthEvent
    data class ChangePassword(val password: String) : AuthEvent
    data object Login : AuthEvent

}