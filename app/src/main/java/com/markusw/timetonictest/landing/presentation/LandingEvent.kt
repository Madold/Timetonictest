package com.markusw.timetonictest.landing.presentation

sealed interface LandingEvent {

    data object CloseSession : LandingEvent

}