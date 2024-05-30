package com.markusw.timetonictest.auth.domain.use_cases

import com.markusw.timetonictest.core.presentation.UiText

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: UiText? = null
)