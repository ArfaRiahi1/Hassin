package com.example.hassin.domain.model

data class Load(
    val id: String,
    val origin: String,
    val originProvince: String,
    val destination: String,
    val destinationProvince: String,
    val weight: Double,
    val price: Double,
    val cargoName: String,
    val packing: String,
    val loadingDate: String
)