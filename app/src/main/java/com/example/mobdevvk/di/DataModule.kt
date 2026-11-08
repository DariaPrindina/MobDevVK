package com.example.mobdevvk.di

import com.example.mobdevvk.data.AppRepositoryImpl
import com.example.mobdevvk.domain.AppRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindAppRepository(impl: AppRepositoryImpl): AppRepository
}
