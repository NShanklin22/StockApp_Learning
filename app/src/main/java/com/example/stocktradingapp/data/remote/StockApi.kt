package com.example.stocktradingapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.Query

interface StockApi {

    suspend fun getListings(
        @Query("key") apiKey: String = API_KEY
    ): ResponseBody

    companion object {
        const val API_KEY = ""
        const val BASE_URL = "https://alphavantage.co"
    }


}