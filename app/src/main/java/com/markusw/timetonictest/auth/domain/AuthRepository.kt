package com.markusw.timetonictest.auth.domain

import com.markusw.timetonictest.core.utils.Result

/**
 * The AuthRepository interface defines the methods that the AuthRepository should implement.
 */
interface AuthRepository {

    suspend fun login(email: String, password: String) : Result<Unit>

    suspend fun logout() : Result<Unit>

}