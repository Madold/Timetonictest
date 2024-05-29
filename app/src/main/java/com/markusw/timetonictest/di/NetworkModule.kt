package com.markusw.timetonictest.di

import com.markusw.timetonictest.network.data.remote.TimeTonicApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    const val TIMETONIC_BASE_URL = "https://timetonic.com/"

    @Provides
    @Singleton
    fun provideRetrofitInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(TIMETONIC_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideTimeTonicApi(retrofit: Retrofit): TimeTonicApi {
        return retrofit.create(TimeTonicApi::class.java)
    }



}