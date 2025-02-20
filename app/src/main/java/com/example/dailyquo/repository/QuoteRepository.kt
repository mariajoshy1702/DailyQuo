package com.example.dailyquo.repository

import com.example.dailyquo.data.Quote
import com.example.dailyquo.data.QuoteDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

class QuoteRepository(private val quoteDao: QuoteDao) {

    fun getAllQuotes(): Flow<List<Quote>> = quoteDao.getAllQuotes()

    fun getRandomQuote(): Flow<Quote> = quoteDao.getRandomQuote()

    suspend fun insertQuote(quote: Quote) {
        quoteDao.insertQuote(quote)
    }

    suspend fun deleteQuote(quote: Quote) {
        quoteDao.deleteQuote(quote)
    }

    suspend fun getQuotesCount(): Int {
        return quoteDao.getAllQuotes().first().size
    }

}
