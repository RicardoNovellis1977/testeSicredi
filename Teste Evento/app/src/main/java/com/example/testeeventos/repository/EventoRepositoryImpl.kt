package com.example.testeeventos.repository

import com.example.testeeventos.model.CheckIn
import com.example.testeeventos.model.Eventos

class EventoRepositoryImpl(private val retrofitService: RetrofitBuilder) : EventoRepository {

    override suspend fun getEvents(): List<Eventos> = retrofitService.apiService.getAllEvents()

    override suspend fun getEvent(id: String): Eventos = retrofitService.apiService.getEvent(id)

    override suspend fun postCheckIn(checkIn: CheckIn) = retrofitService.apiService.postCheckIn(checkIn)
}