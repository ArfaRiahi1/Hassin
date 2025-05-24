package com.example.hassin.di

import com.example.hassin.data.repository.LoadRepositoryImpl
import com.example.hassin.domain.repository.LoadRepository
import com.example.hassin.domain.usecase.GetLoadsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLoadRepository(): LoadRepository {
        // return your LoadRepository implementation here
        return LoadRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideGetLoadsUseCase(repository: LoadRepository): GetLoadsUseCase {
        return GetLoadsUseCase(repository)
    }
}