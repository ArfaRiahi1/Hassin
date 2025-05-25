package com.example.hassin.domain.model

data class Load(
    val id: Int,
    val originCity: String,
    val originProvince: String,
    val destinationCity: String,
    val destinationProvince: String,
    val weightInTon: Int,
    val priceInMillionToman: Double,
    val packaging: String,
    val loadingDate: String,
    val itemName: String
)