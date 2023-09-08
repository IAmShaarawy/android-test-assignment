package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.data.remote.AuthInterceptor
import com.example.shacklehotelbuddy.foundation.json
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.MediaType.Companion.toMediaType
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HttpClientModule {
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .build()

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl(domain)
        .addConverterFactory(DefaultJsonConverter)
        .build()

    companion object {
        private const val domain = "https://hotels4.p.rapidapi.com/"
        private val jsonMediaType = "application/json".toMediaType()

        val DefaultJsonConverter = json.asConverterFactory(jsonMediaType)
    }
}