package com.markusw.timetonictest.landing.presentation

/**
 * Sealed class to represent events that can be triggered from the [LandingFragment].
 */
sealed interface LandingEvent {

    data object CloseSession : LandingEvent

}