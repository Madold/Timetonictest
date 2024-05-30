package com.markusw.timetonictest.auth.domain

import com.markusw.timetonictest.core.presentation.UiText
import com.markusw.timetonictest.core.utils.Result

interface AuthRepository {

    suspend fun login(email: String, password: String) : Result<Unit>

}