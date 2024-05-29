package com.markusw.timetonictest.auth.data.repository

import com.markusw.timetonictest.auth.domain.AuthRepository
import com.markusw.timetonictest.core.presentation.UiText
import com.markusw.timetonictest.core.utils.Result
import com.markusw.timetonictest.network.data.remote.TimeTonicService

class AndroidAuthRepository(
    private val timeTonicService: TimeTonicService
) : AuthRepository {
    override suspend fun getAppKey(): Result<String> {
        return try {
            Result.Success(timeTonicService.getAppKey().appKey)
        } catch (e: Exception) {
            Result.Error(UiText.DynamicString("${e.message}"))
        }
    }

    override suspend fun getOauthKey(appKey: String, login: String, pwd: String): Result<String> {
        return try {
            Result.Success(timeTonicService.getOauthKey(appKey, login, pwd).oauthKey)
        } catch (e: Exception) {
            Result.Error(UiText.DynamicString("${e.message}"))
        }
    }

    override suspend fun getSessionKey(o_u: String, u_c: String, oauthKey: String): Result<String> {
        return try {
            Result.Success(timeTonicService.getSessionKey(o_u, u_c, oauthKey).sessionKey)
        } catch (e: Exception) {
            Result.Error(UiText.DynamicString("${e.message}"))
        }
    }


}