package com.sekar.implementasiapi.data.api

import com.sekar.implementasiapi.data.model.Day
import com.sekar.implementasiapi.data.model.WeatherData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v1/forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") cityName : String,
        @Query("days") day: Int,
        @Query("aqi") aqi : String,
        @Query("alerts") alerts: String
    ) : WeatherData

}