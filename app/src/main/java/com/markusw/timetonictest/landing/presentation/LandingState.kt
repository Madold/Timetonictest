package com.markusw.timetonictest.landing.presentation

import com.markusw.timetonictest.landing.domain.model.Book

data class LandingState(
    val isLoading: Boolean = false,
    val isClosingSession: Boolean = false,
    val books: List<Book> = emptyList(),
)
