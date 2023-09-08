package com.example.shacklehotelbuddy.di

import com.example.shacklehotelbuddy.data.repos.SearchRepo
import com.example.shacklehotelbuddy.data.repos.SearchRepoImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface ReposModule {
    @Binds
    fun provideSearchRepo(trendsRepoImpl: SearchRepoImpl): SearchRepo
}