package com.example.hassin.domain.repository

import com.example.hassin.domain.model.Load

interface LoadRepository {
    suspend fun getLoads(page: Int, pageSize: Int): List<Load>
    suspend fun getLoadById(id: String): Load?
}