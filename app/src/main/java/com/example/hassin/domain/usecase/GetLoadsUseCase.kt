package com.example.hassin.domain.usecase

import com.example.hassin.domain.model.Load
import com.example.hassin.domain.repository.LoadRepository

class GetLoadsUseCase(
    private val repository: LoadRepository
) {
    suspend operator fun invoke(page: Int): List<Load> {
        return repository.getLoads(page)
    }
}
