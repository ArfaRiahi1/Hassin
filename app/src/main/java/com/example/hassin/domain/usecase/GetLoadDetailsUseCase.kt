package com.example.hassin.domain.usecase

import com.example.hassin.domain.repository.LoadRepository
import com.example.hassin.domain.model.Load
import javax.inject.Inject

class GetLoadDetailUseCase @Inject constructor(
    private val repository: LoadRepository
) {
    suspend operator fun invoke(id: String): Load? =
        repository.getLoadById(id)
}