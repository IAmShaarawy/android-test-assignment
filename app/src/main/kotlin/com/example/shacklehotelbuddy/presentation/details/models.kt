package com.example.shacklehotelbuddy.presentation.details

data class Property(
    val name: String,
    val neighborhoodName: String,
    val formattedPrice: String,
    val rating: Double,
    val imgUrl: String
)