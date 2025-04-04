package com.example.myapplication

import java.io.IOException


class SmsRepository {
    suspend fun getCountries(countryId: String): List<Country> {
        val response = RetrofitClient.instance.getCountries(countryId)
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw IOException("HTTP Error: ${response.code()}")
        }
    }

    suspend fun getServices(serviceId: String): List<Service> {
        val response = RetrofitClient.instance.getServices(serviceId)
        return if (response.isSuccessful) {
            response.body() ?: emptyList()
        } else {
            throw IOException("HTTP Error: ${response.code()}")
        }
    }
}
