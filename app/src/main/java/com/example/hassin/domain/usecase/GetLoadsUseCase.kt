package com.example.hassin.domain.usecase

import com.example.hassin.domain.repository.LoadRepository
import com.example.hassin.domain.model.Load
import javax.inject.Inject

class GetLoadsUseCase @Inject constructor(
    private val repository: LoadRepository
) {
    suspend operator fun invoke(page: Int, pageSize: Int): List<Load> =
        repository.getLoads(page, pageSize)
}