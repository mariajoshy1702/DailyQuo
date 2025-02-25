package com.example.dailyquo.data.api

import retrofit2.http.GET

interface QuoteApiService {
    @GET("api/random/")
    suspend fun getRandomQuote(): List<QuoteResponse>
}
