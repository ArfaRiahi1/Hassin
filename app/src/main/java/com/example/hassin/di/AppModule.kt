package com.example.hassin.di

import com.example.hassin.data.repository.FakeLoadRepository
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
        return FakeLoadRepository()
    }

    @Provides
    @Singleton
    fun provideGetLoadsUseCase(
        repository: LoadRepository
    ): GetLoadsUseCase = GetLoadsUseCase(repository)
}
