package com.markusw.timetonictest.auth.domain

import com.markusw.timetonictest.core.utils.Result

interface AuthRepository {

    suspend fun getAppKey(): Result<String>

    suspend fun getOauthKey(appKey: String, login: String, pwd: String): Result<String>

    suspend fun getSessionKey(o_u: String, u_c: String, oauthKey: String): Result<String>

}