package com.example.testeeventos.di

import com.example.testeeventos.repository.EventoRepository
import com.example.testeeventos.repository.EventoRepositoryImpl
import com.example.testeeventos.repository.RetrofitBuilder
import com.example.testeeventos.util.ProvideHttpClient.provideHttpClient
import org.koin.dsl.module

val dataModule = module {
    factory { provideHttpClient() }
    factory { RetrofitBuilder() }
    factory<EventoRepository> { EventoRepositoryImpl(get()) }
}