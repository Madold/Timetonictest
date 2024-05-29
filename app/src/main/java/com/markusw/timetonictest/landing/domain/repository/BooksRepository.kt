package com.markusw.timetonictest.landing.domain.repository

import com.markusw.timetonictest.network.data.remote.responses.BookResponse
import com.markusw.timetonictest.core.utils.Result

interface BooksRepository {
    suspend fun getAllBooks(
        o_u: String,
        u_c: String,
        sessionKey: String
    ): Result<BookResponse>

}