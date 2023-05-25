package com.example.exchangerates.data.remote

import com.example.exchangerates.data.remote.dto.GetLatestResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ExchangeRatesApi {

    @GET("latest/")
    suspend fun getLatest(
        @Query("base") base: String,
        @Query("places") places: Int,
    ): GetLatestResponse
}