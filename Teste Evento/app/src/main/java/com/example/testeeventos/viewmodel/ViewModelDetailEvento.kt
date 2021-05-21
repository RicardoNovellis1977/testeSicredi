package com.example.testeeventos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeeventos.model.Eventos
import com.example.testeeventos.repository.EventoRepository
import kotlinx.coroutines.launch

class ViewModelDetailEvento(private val eventoRepository: EventoRepository) : ViewModel() {

    val eventoData = MutableLiveData<Eventos>()
    val error = MutableLiveData<Boolean>()

    fun getEvent(id: String) {
        viewModelScope.launch {
            try {
                error.value = false
                eventoData.value = eventoRepository.getEvent(id)
            } catch (e: Exception) {
                error.value = true
            }
        }
    }

}