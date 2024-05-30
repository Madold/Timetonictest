package com.markusw.timetonictest.landing.presentation

import com.markusw.timetonictest.core.presentation.UiText

sealed interface LandingViewModelEvent {

    data object CloseSessionSuccess : LandingViewModelEvent

    data class CloseSessionError(val message: UiText) : LandingViewModelEvent

}