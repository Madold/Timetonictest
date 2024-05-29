package com.markusw.timetonictest.landing.data.repository

import com.markusw.timetonictest.core.presentation.UiText
import com.markusw.timetonictest.core.utils.Result
import com.markusw.timetonictest.landing.domain.repository.BooksRepository
import com.markusw.timetonictest.network.data.remote.TimeTonicService
import com.markusw.timetonictest.network.data.remote.responses.BookResponse

class AndroidBooksRepository (
    private val timeTonicService: TimeTonicService
): BooksRepository {
    override suspend fun getAllBooks(
        o_u: String,
        u_c: String,
        sessionKey: String
    ): Result<BookResponse> {
        return try {
            Result.Success(timeTonicService.getAllBooks(o_u, u_c, sessionKey))
        } catch (e: Exception) {
            Result.Error(UiText.DynamicString("${e.message}"))
        }
    }
}