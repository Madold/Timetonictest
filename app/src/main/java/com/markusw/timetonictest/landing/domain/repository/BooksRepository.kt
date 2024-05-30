package com.markusw.timetonictest.landing.domain.repository

import com.markusw.timetonictest.core.utils.Result
import com.markusw.timetonictest.network.data.remote.responses.BooksResponse

/**
 * Repository for books.
 */
interface BooksRepository {
    /**
     * Get all books.
     * @return Result<BooksResponse> The result of the operation
     */
    suspend fun getAllBooks(): Result<BooksResponse>

}