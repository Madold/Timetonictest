package com.markusw.timetonictest.landing.presentation

import com.markusw.timetonictest.core.presentation.UiText

/**
 * Sealed class to represent the events that can be triggered by the [LandingViewModel].
 */
sealed interface LandingViewModelEvent {

    data object CloseSessionSuccess : LandingViewModelEvent

    data class CloseSessionError(val message: UiText) : LandingViewModelEvent

}