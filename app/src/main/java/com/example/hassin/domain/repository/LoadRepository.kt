package com.example.hassin.domain.repository

import com.example.hassin.domain.model.Load

interface LoadRepository {
    suspend fun getLoads(page: Int): List<Load>
}