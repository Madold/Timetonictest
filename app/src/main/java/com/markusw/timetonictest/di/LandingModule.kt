package com.markusw.timetonictest.di

import com.markusw.timetonictest.core.domain.local.LocalDataStore
import com.markusw.timetonictest.landing.data.repository.AndroidBooksRepository
import com.markusw.timetonictest.landing.domain.repository.BooksRepository
import com.markusw.timetonictest.network.data.remote.TimeTonicApi
import com.markusw.timetonictest.network.data.remote.TimeTonicService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LandingModule {

    @Provides
    @Singleton
    fun provideBooksRepository(
        timeTonicApi: TimeTonicApi,
        localDataStore: LocalDataStore
    ): BooksRepository {
        return AndroidBooksRepository(
            TimeTonicService(timeTonicApi),
            localDataStore
        )
    }

}