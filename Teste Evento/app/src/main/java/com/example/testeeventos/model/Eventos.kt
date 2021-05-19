package com.example.testeeventos.model

data class Eventos(
    val id: String?,
    val title: String,
    val description: String,
    val longitude: String,
    val latitude: String?,
    val date: String,
    val price: Double,
    var image: String
)