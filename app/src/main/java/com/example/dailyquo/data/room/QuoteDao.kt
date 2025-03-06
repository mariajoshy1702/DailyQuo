package com.example.dailyquo.data.room

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuote(quote: Quote)

    @Query("SELECT * FROM quotes ORDER BY id DESC")
    fun getAllQuotes(): Flow<List<Quote>>

    @Delete
    suspend fun deleteQuote(quote: Quote)

    @Query("SELECT * FROM quotes WHERE text = :text LIMIT 1")
    fun getQuoteByText(text: String): Quote?

    @Query("DELETE FROM quotes WHERE text = :text")
    suspend fun deleteQuoteByText(text: String)

}
