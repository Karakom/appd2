package com.example.myapplication

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("v1/activations/services")
    suspend fun getServices(serviceId: String): Response<List<Service>>

    @GET("v1/activations/services/{serviceId}/countries")
    suspend fun getCountries(@Path("serviceId") serviceId: String): Response<List<Country>>
}