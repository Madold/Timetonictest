package com.markusw.timetonictest.auth.data.repository

import com.markusw.timetonictest.auth.domain.AuthRepository
import com.markusw.timetonictest.core.domain.local.LocalDataStore
import com.markusw.timetonictest.core.presentation.UiText
import com.markusw.timetonictest.core.utils.Constants.O_U
import com.markusw.timetonictest.core.utils.Constants.SESSION_KEY
import com.markusw.timetonictest.core.utils.Result
import com.markusw.timetonictest.network.data.remote.TimeTonicService

class AndroidAuthRepository(
    private val timeTonicService: TimeTonicService,
    private val localDataStore: LocalDataStore
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

            localDataStore.saveData(data = sessionKey, key = SESSION_KEY)
            localDataStore.saveData(data = oAuthKeyResponse.o_u, key = O_U)

            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(UiText.DynamicString("${e.message}"))
        }
    }

    override suspend fun logout(): Result<Unit> {
        return try {
            val sessionKey = localDataStore.getData(SESSION_KEY, defaultValue = "none")
            val o_u = localDataStore.getData(O_U, defaultValue = "none")
            timeTonicService.dropAllSessions(o_u, o_u, sessionKey)
            localDataStore.remove(SESSION_KEY)
            localDataStore.remove(O_U)
            Result.Success(Unit)
        } catch (e: Exception) {
            e.printStackTrace()
            Result.Error(UiText.DynamicString("${e.message}"))
        }
    }


}