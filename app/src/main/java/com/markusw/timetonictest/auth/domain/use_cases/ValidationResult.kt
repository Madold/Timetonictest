package com.markusw.timetonictest.auth.domain.use_cases

import com.markusw.timetonictest.core.presentation.UiText

/**
 * Data class for validation results. Contains a boolean indicating whether the validation was successful and an optional error message.
 * @param successful Boolean indicating whether the validation was successful.
 * @param errorMessage Optional error message.
 * @see UiText
 */
data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)