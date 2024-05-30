package com.markusw.timetonictest.auth.domain.use_cases

import com.markusw.timetonictest.R
import com.markusw.timetonictest.core.presentation.UiText
import javax.inject.Inject

class ValidatePassword @Inject constructor() {

    operator fun invoke(password: String): ValidationResult {

        if (password.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.blank_password_error)
            )
        }

        return ValidationResult(
            successful = true
        )

    }

}