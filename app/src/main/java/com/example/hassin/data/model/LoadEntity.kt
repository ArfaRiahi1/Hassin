package com.example.hassin.data.model

import com.example.hassin.domain.model.Load

data class LoadEntity(
    val id: String,
    val origin: Pair<String, String>,
    val destination: Pair<String, String>,
    val weight: Int,
    val price: Float,
    val kind: String,
    val packing: String,
    val loadingDate: String,
) {
    fun toDomain() = Load(id, origin, destination, weight, price, kind, packing, loadingDate)
}
