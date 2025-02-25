package com.example.dailyquo.repository

import com.example.dailyquo.data.api.QuoteApiService
import com.example.dailyquo.data.room.Quote
import com.example.dailyquo.data.room.QuoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class QuoteRepository(private val quoteDao: QuoteDao, private val apiService: QuoteApiService) {

    fun getAllQuotes(): Flow<List<Quote>> = quoteDao.getAllQuotes()

    suspend fun insertQuote(quote: Quote) {
        quoteDao.insertQuote(quote)
    }

    suspend fun deleteQuote(quote: Quote) {
        quoteDao.deleteQuote(quote)
    }

    fun getRandomQuote(): Flow<Quote> = flow {
        val localQuotes = quoteDao.getAllQuotes().first()
        val shouldFetchFromApi = localQuotes.isEmpty() || Random.nextBoolean()

        if (shouldFetchFromApi) {
            try {
                val apiResponse = apiService.getRandomQuote()
                if (apiResponse.isNotEmpty()) {
                    val quoteData = apiResponse[0]
                    emit(
                        Quote(
                            text = quoteData.q,
                            author = quoteData.a
                        )
                    )
                } else if (localQuotes.isNotEmpty()) {
                    emit(localQuotes.random())
                }
            } catch (e: Exception) {

                if (localQuotes.isNotEmpty()) {
                    emit(localQuotes.random())
                }
            }
        } else {
            emit(localQuotes.random())
        }
    }
}
