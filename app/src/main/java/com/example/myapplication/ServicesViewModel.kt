package com.example.myapplication

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ServicesViewModel : ViewModel() {
    private val repository = SmsRepository()
    val services = MutableLiveData<List<Service>>()
    val error = MutableLiveData<String>()

    // CORRECT METHOD (no serviceId parameter)
    fun loadServices() {
        viewModelScope.launch {
            try {
                services.value = repository.getServices() // ✅ No parameters
            } catch (e: Exception) {
                error.value = "Error: ${e.message}"
            }
        }
    }
}

class CountriesViewModel : ViewModel() {
    private val repository = SmsRepository()
    val countries = MutableLiveData<List<Country>>()
    val error = MutableLiveData<String>()

    fun loadCountries(serviceId: String) {
        viewModelScope.launch {
            try {
                countries.value = repository.getCountries(serviceId)
            } catch (e: Exception) {
                error.value = "Error loading countries: ${e.message}"
            }
        }
    }
}