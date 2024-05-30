package com.markusw.timetonictest.landing.domain.repository

import com.markusw.timetonictest.network.data.remote.responses.BookResponse
import com.markusw.timetonictest.core.utils.Result
import com.markusw.timetonictest.network.data.remote.responses.BooksResponse

interface BooksRepository {
    suspend fun getAllBooks(): Result<BooksResponse>

}