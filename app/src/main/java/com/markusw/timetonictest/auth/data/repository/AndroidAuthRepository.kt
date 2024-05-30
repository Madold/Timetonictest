package com.markusw.timetonictest.auth.data.repository

import com.markusw.timetonictest.auth.domain.AuthRepository
import com.markusw.timetonictest.core.presentation.UiText
import com.markusw.timetonictest.core.utils.Result
import com.markusw.timetonictest.network.data.remote.TimeTonicService

class AndroidAuthRepository(
    private val timeTonicService: TimeTonicService
) : AuthRepository {

    override suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            val appKey = timeTonicService.getAppKey().appkey
            val oAuthKeyResponse = timeTonicService.getOauthKey(appKey, email, password)
            val sessionKey = timeTonicService.getSessionKey(
                o_u = oAuthKeyResponse.o_u,
                u_c = oAuthKeyResponse.o_u,
                oauthKey = oAuthKeyResponse.oauthkey
            ).sessionKey

            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(UiText.DynamicString("${e.message}"))
        }
    }


}