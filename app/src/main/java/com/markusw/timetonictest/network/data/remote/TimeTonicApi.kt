package com.markusw.timetonictest.network.data.remote

import com.markusw.timetonictest.network.data.remote.responses.AppKeyResponse
import com.markusw.timetonictest.network.data.remote.responses.BookResponse
import com.markusw.timetonictest.network.data.remote.responses.BooksResponse
import com.markusw.timetonictest.network.data.remote.responses.DropSessionResponse
import com.markusw.timetonictest.network.data.remote.responses.OauthKeyResponse
import com.markusw.timetonictest.network.data.remote.responses.SessionKeyResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

interface TimeTonicApi {

    @POST("live/api.php")
    suspend fun getAppKey(
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "createAppkey",
        @Query("appname") appName: String = "timetonictest",
    ): Response<AppKeyResponse>

    @POST("live/api.php")
    suspend fun getOauthKey(
        @Query("appkey") appKey: String,
        @Query("login") login: String,
        @Query("pwd") pwd: String,
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "createOauthkey",
    ): Response<OauthKeyResponse>

    @POST("live/api.php")
    suspend fun getSessionKey(
        @Query("o_u") o_u: String,
        @Query("u_c") u_c: String,
        @Query("oauthkey") oauthKey: String,
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "createSesskey",
    ): Response<SessionKeyResponse>

    @POST("live/api.php")
    suspend fun getAllBooks(
        @Query("o_u") o_u: String,
        @Query("u_c") u_c: String,
        @Query("sesskey") sessionKey: String,
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "getAllBooks",
    ): Response<BooksResponse>

    @POST("live/api.php")
    suspend fun dropAllSessions(
        @Query("o_u") o_u: String,
        @Query("u_c") u_c: String,
        @Query("sesskey") sessionKey: String,
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "dropAllSessions",
    ): Response<DropSessionResponse>

}