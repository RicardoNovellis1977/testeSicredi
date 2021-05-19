package com.example.testeeventos.repository

import com.example.testeeventos.model.CheckIn
import com.example.testeeventos.model.Eventos
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiEventos {

    @GET("api/events")
    suspend fun getAllEvents(): List<Eventos>

    @GET("api/events/{id}")
    suspend fun getEvent(@Path("id") id: Int): Eventos

    @POST("/checkin")
    suspend fun postCheckIn(@Body checkIn: CheckIn)
}