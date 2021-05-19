package com.example.testeeventos.repository

import com.example.testeeventos.model.CheckIn
import com.example.testeeventos.model.Eventos

interface EventoRepository {
    suspend fun getEvents(): List<Eventos>
    suspend fun getEvent(id: String): Eventos
    suspend fun postCheckIn(checkIn: CheckIn)
}