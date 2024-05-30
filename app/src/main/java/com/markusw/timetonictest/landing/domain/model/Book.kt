package com.markusw.timetonictest.landing.domain.model

/**
 * Represents a book.
 *
 * @property name The name of the book.
 * @property coverUrl The URL of the cover image of the book.
 */
data class Book(
    val name: String,
    val coverUrl: String,
)
