package com.example.dailyquo.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    val retrofit: QuoteApiService = Retrofit.Builder()
        .baseUrl("https://zenquotes.io/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(QuoteApiService::class.java)

}
