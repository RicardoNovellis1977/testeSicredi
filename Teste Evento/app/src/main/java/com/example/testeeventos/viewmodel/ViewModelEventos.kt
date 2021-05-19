package com.example.testeeventos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeeventos.model.Eventos
import com.example.testeeventos.repository.EventoRepository
import kotlinx.coroutines.launch

class ViewModelEventos (private val eventoRepository: EventoRepository) : ViewModel() {

    val events = MutableLiveData<List<Eventos>>()
    val error = MutableLiveData<Boolean>()

    fun getEvents() {
        viewModelScope.launch {
            try {
                error.value = false
                events.value = eventoRepository.getEvents()
            }catch (e: Exception){
                error.value = true
            }
        }
    }
}