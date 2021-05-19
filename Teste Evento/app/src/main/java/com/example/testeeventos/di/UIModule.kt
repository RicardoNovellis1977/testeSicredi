package com.example.testeeventos.di

import com.example.testeeventos.viewmodel.ViewModelEventos
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    viewModel { ViewModelEventos(get()) }
    //viewModel { EventDetailViewModel(get()) }
}