package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.foundation.DispatchersProvider
import com.example.shacklehotelbuddy.foundation.DispatchersProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DispatcherProviderModule {
    @Singleton
    @Provides
    fun provideDispatchersProvider(): DispatchersProvider = DispatchersProviderImpl()
}