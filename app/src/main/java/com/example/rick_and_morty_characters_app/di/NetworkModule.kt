package com.example.rick_and_morty_characters_app.di

import com.example.rick_and_morty_characters_app.model.dataSource.network.apiService
import com.example.rick_and_morty_characters_app.utils.Constants.BASE_URL
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

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    @Provides
    @Singleton
    fun provideRickAndMortyApi(retrofit: Retrofit): apiService {
        return retrofit.create(apiService::class.java)
    }

}
