package com.markusw.timetonictest.landing.presentation

import com.markusw.timetonictest.landing.domain.model.Book

/**
 * State for the Landing screen.
 *
 * @property isLoading True if the screen is loading data.
 * @property isClosingSession True if the session is being closed.
 * @property books The list of books to display.
 */
data class LandingState(
    val isLoading: Boolean = false,
    val isClosingSession: Boolean = false,
    val books: List<Book> = emptyList(),
)
