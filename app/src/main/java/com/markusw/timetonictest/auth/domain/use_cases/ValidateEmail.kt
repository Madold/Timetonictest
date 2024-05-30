package com.markusw.timetonictest.auth.domain.use_cases

import com.markusw.timetonictest.R
import com.markusw.timetonictest.core.presentation.UiText
import javax.inject.Inject

/**
 * Use case to validate an email address.
 *
 * @param email The email address to validate.
 * @return A [ValidationResult] object indicating whether the email address is valid or not.
 * @see ValidationResult
 */
class ValidateEmail @Inject constructor() {

    companion object {
        private const val EMAIL_REGEX = "^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"
    }

    operator fun invoke(email: String): ValidationResult {

        if (email.isBlank()) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.blank_email_error)
            )
        }

        if (!email.matches(EMAIL_REGEX.toRegex())) {
            return ValidationResult(
                successful = false,
                errorMessage = UiText.StringResource(R.string.invalid_email_error)
            )
        }

        return ValidationResult(successful = true)
    }

}