package com.markusw.timetonictest.network.data.remote

import com.markusw.timetonictest.network.data.remote.responses.AppKeyResponse
import com.markusw.timetonictest.network.data.remote.responses.BooksResponse
import com.markusw.timetonictest.network.data.remote.responses.DropSessionResponse
import com.markusw.timetonictest.network.data.remote.responses.OauthKeyResponse
import com.markusw.timetonictest.network.data.remote.responses.SessionKeyResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interface for the TimeTonic API
 * This interface contains all the endpoints for the TimeTonic API
 * The endpoints are called using the Retrofit library
 */
interface TimeTonicApi {

    /**
     * Get the app key
     * @param version The version of the API
     * @param req The request type
     * @param appName The name of the app
     * @return The response of the API
     */
    @POST("live/api.php")
    suspend fun getAppKey(
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "createAppkey",
        @Query("appname") appName: String = "timetonictest",
    ): Response<AppKeyResponse>

    /**
     * Get the oauth key
     * @param appKey The app key
     * @param login The login
     * @param pwd The password
     * @param version The version of the API
     * @param req The request type
     * @return The response of the API
     */

    @POST("live/api.php")
    suspend fun getOauthKey(
        @Query("appkey") appKey: String,
        @Query("login") login: String,
        @Query("pwd") pwd: String,
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "createOauthkey",
    ): Response<OauthKeyResponse>

    /**
     * Get the session key
     * @param o_u The login
     * @param u_c The password
     * @param oauthKey The oauth key
     * @param version The version of the API
     * @param req The request type
     * @return The response of the API
     */
    @POST("live/api.php")
    suspend fun getSessionKey(
        @Query("o_u") o_u: String,
        @Query("u_c") u_c: String,
        @Query("oauthkey") oauthKey: String,
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "createSesskey",
    ): Response<SessionKeyResponse>

    /**
     * Get all books
     * @param o_u The login
     * @param u_c The password
     * @param sessionKey The session key
     * @param version The version of the API
     * @param req The request type
     * @return The response of the API
     */
    @POST("live/api.php")
    suspend fun getAllBooks(
        @Query("o_u") o_u: String,
        @Query("u_c") u_c: String,
        @Query("sesskey") sessionKey: String,
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "getAllBooks",
    ): Response<BooksResponse>

    /**
     * Drop all sessions
     * @param o_u The login
     * @param u_c The password
     * @param sessionKey The session key
     * @param version The version of the API
     * @param req The request type
     * @return The response of the API
     */
    @POST("live/api.php")
    suspend fun dropAllSessions(
        @Query("o_u") o_u: String,
        @Query("u_c") u_c: String,
        @Query("sesskey") sessionKey: String,
        @Query("version") version: String = "6.49q/6.49",
        @Query("req") req: String = "dropAllSessions",
    ): Response<DropSessionResponse>

}