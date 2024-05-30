package com.markusw.timetonictest.network.data.remote

import com.markusw.timetonictest.network.data.remote.responses.AppKeyResponse
import com.markusw.timetonictest.network.data.remote.responses.BookResponse
import com.markusw.timetonictest.network.data.remote.responses.OauthKeyResponse
import com.markusw.timetonictest.network.data.remote.responses.SessionKeyResponse

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

    /**
     * Parameters
     *
     * string version Version of API/serveur. See history of changes.
     *
     * string [o_u] Oauth userid, must match Session key.
     *
     * string [u_c] Userid (for now must be same as o_u).
     *
     * string [oauthkey] Key for a give authorized (logged in) user.
     */
    suspend fun getSessionKey(o_u: String, u_c: String, oauthKey: String): SessionKeyResponse {
        val response = timeTonicApi.getSessionKey(o_u, u_c, oauthKey)

        if (!response.isSuccessful) {
            println("session key failed")
            throw Exception("Error: ${response.code()}")
        }

        return response.body() ?: throw Exception("Error: body is null")
    }

    suspend fun getAllBooks(o_u: String, u_c: String, sessionKey: String): BookResponse {

        val response = timeTonicApi.getAllBooks(o_u, u_c, sessionKey)

        if (!response.isSuccessful) {
            println("get all books failed")
            throw Exception("Error: ${response.code()}")
        }

        return response.body() ?: throw Exception("Error: body is null")
    }

}