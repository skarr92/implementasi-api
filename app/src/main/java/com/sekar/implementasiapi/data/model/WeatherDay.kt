package com.sekar.implementasiapi.data.model


import com.google.gson.annotations.SerializedName

data class WeatherDay(
    @SerializedName("city")
    val city: City?,
    @SerializedName("cnt")
    val cnt: Int?,
    @SerializedName("cod")
    val cod: String?,
    @SerializedName("list")
    val list: List?,
    @SerializedName("message")
    val message: Int?
)