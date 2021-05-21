package com.example.testeeventos.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testeeventos.model.CheckIn
import com.example.testeeventos.repository.EventoRepository
import kotlinx.coroutines.launch

class ViewModelCheckIn(private val eventRepository: EventoRepository) : ViewModel() {

    val resultCheckinLiveData : MutableLiveData<Boolean> = MutableLiveData()

    fun postCheckIn (checkIn: CheckIn) {
        viewModelScope.launch {
            try {
                eventRepository.postCheckIn(checkIn)
                resultCheckinLiveData.value = true
            } catch (ex: Exception) {
                resultCheckinLiveData.value = false
            }

        }
    }
}