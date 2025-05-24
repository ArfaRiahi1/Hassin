package com.example.hassin.data.repository

import com.example.hassin.data.model.LoadEntity
import com.example.hassin.domain.model.Load
import com.example.hassin.domain.repository.LoadRepository

class LoadRepositoryImpl : LoadRepository {
    private val loads = listOf(
        LoadEntity("1", Pair("تهران", "تهران"), Pair("کرمان", "کرمان"), 12, 2f,"سیمان","گونی","2 اردیبهشت"),
        LoadEntity("2", Pair("مشهد", "خراسان"), Pair("اهواز", "خوزستان"), 9, 3f,"برنج","گونی","5 بهمن"),
        LoadEntity("3", Pair("تبریز", "تبریز"), Pair("همدان", "همدان"), 10, 3f,"آجر","پالت","25 دی"),
        LoadEntity("4", Pair("تهران", "تهران"), Pair("ساری", "مازندران"), 7, 2.5f,"غلات","کارتن","14 آذر"),
    )

    override suspend fun getLoads(): List<Load> = loads.map { it.toDomain() }

    override suspend fun getLoadById(id: String): Load? = loads.find { it.id == id }?.toDomain()
}