package com.example.testeeventos.di

import com.example.testeeventos.viewmodel.ViewModelCheckIn
import com.example.testeeventos.viewmodel.ViewModelDetailEvento
import com.example.testeeventos.viewmodel.ViewModelEventos
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ViewModelEventos(get()) }
    viewModel { ViewModelDetailEvento(get())}
    viewModel { ViewModelCheckIn(get()) }}
