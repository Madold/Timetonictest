package com.markusw.timetonictest.network.data.remote

import com.markusw.timetonictest.network.data.remote.responses.AppKeyResponse
import com.markusw.timetonictest.network.data.remote.responses.BooksResponse
import com.markusw.timetonictest.network.data.remote.responses.OauthKeyResponse
import com.markusw.timetonictest.network.data.remote.responses.SessionKeyResponse

/**
 * Service class to handle all the network calls
 * @param timeTonicApi the retrofit api interface
 */
class TimeTonicService(
    private val timeTonicApi: TimeTonicApi
) {
    suspend fun getAppKey(): AppKeyResponse {
        val response = timeTonicApi.getAppKey()

        if (!response.isSuccessful) {
            throw Exception("Error: ${response.code()}")
        }
        return response.body() ?: throw Exception("Error: body is null")
    }

    suspend fun getOauthKey(appKey: String, login: String, pwd: String): OauthKeyResponse {
        val response = timeTonicApi.getOauthKey(appKey, login, pwd)

        if (!response.isSuccessful) {
            throw Exception("Error: ${response.code()}")
        }

        return response.body() ?:  throw Exception("Error: body is null")
    }

    suspend fun getSessionKey(o_u: String, u_c: String, oauthKey: String): SessionKeyResponse {
        val response = timeTonicApi.getSessionKey(o_u, u_c, oauthKey)

        if (!response.isSuccessful) {
            println("session key failed")
            throw Exception("Error: ${response.code()}")
        }

        return response.body() ?: throw Exception("Error: body is null")
    }

    suspend fun getAllBooks(o_u: String, u_c: String, sessionKey: String): BooksResponse {

        val response = timeTonicApi.getAllBooks(o_u, u_c, sessionKey)

        if (!response.isSuccessful) {
            println("get all books failed")
            throw Exception("Error: ${response.code()}")
        }

        println("get all books success")

        return response.body() ?: throw Exception("Error: body is null")
    }

    suspend fun dropAllSessions(o_u: String, u_c: String, sessionKey: String) {
        val response = timeTonicApi.dropAllSessions(o_u, u_c, sessionKey)

        println("close session success")

        if (!response.isSuccessful) {
            throw Exception("Error: ${response.code()}")
        }
    }

}