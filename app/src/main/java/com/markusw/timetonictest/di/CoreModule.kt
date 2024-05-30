package com.markusw.timetonictest.di

import android.content.Context
import com.markusw.timetonictest.core.data.local.AndroidLocalDataStore
import com.markusw.timetonictest.core.domain.local.LocalDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideLocalDataStore(@ApplicationContext context: Context): LocalDataStore {
        return AndroidLocalDataStore(context)
    }

}