package com.example.testeeventos.repository

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://5f5a8f24d44d640016169133.mockapi.io")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiEventos = getRetrofit().create(ApiEventos::class.java)
}