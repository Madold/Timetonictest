package com.markusw.timetonictest.landing.data.repository

import com.markusw.timetonictest.core.domain.local.LocalDataStore
import com.markusw.timetonictest.core.presentation.UiText
import com.markusw.timetonictest.core.utils.Constants
import com.markusw.timetonictest.core.utils.Result
import com.markusw.timetonictest.landing.domain.repository.BooksRepository
import com.markusw.timetonictest.network.data.remote.TimeTonicService
import com.markusw.timetonictest.network.data.remote.responses.BookResponse
import com.markusw.timetonictest.network.data.remote.responses.BooksResponse

class AndroidBooksRepository (
    private val timeTonicService: TimeTonicService,
    private val localDataStore: LocalDataStore
): BooksRepository {
    override suspend fun getAllBooks(): Result<BooksResponse> {
        val o_u = localDataStore.getData(key = Constants.O_U, defaultValue = "none")
        val sessionKey = localDataStore.getData(key = Constants.SESSION_KEY, defaultValue = "none")

        return try {
            println(timeTonicService.getAllBooks(o_u, o_u, sessionKey))
            Result.Success(timeTonicService.getAllBooks(o_u, o_u, sessionKey))
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(UiText.DynamicString("${e.message}"))
        }
    }
}