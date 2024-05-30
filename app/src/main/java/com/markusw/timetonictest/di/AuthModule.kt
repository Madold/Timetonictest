package com.markusw.timetonictest.di

import com.markusw.timetonictest.auth.data.repository.AndroidAuthRepository
import com.markusw.timetonictest.auth.domain.AuthRepository
import com.markusw.timetonictest.core.domain.local.LocalDataStore
import com.markusw.timetonictest.network.data.remote.TimeTonicApi
import com.markusw.timetonictest.network.data.remote.TimeTonicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        timeTonicApi: TimeTonicApi,
        localDataStore: LocalDataStore
    ): AuthRepository {
        return AndroidAuthRepository(
            TimeTonicService(timeTonicApi),
            localDataStore
        )
    }

}