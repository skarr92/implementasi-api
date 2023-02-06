package com.sekar.implementasiapi.data.api

import com.sekar.implementasiapi.data.model.WeatherDay
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/forecast")

    suspend fun getForecast(
        @Query("q") cityName : String,
        @Query("appid") apiKey : String,
        @Query("units") units : String,
    ) : WeatherDay

}