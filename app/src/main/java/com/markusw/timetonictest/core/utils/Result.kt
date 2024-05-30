package com.markusw.timetonictest.core.utils

import com.markusw.timetonictest.core.presentation.UiText

/**
 * A generic class that holds a value with its loading status.
 * @param <T> The type of the data resource
 * @property data The data resource
 * @property message The message to be displayed
 */
sealed class Result<T>(val data: T? = null, var message: UiText? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(message: UiText) : Result<T>(null, message)
}