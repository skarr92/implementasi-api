package com.sekar.implementasiapi.data.repository

import com.sekar.implementasiapi.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getForecast() = apiHelper.getForecast()
}