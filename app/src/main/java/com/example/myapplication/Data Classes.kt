package com.example.myapplication

import com.google.gson.annotations.SerializedName

data class Service(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("available") val available: Int
)

data class Country(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("available") val available: Int
)