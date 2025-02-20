package com.example.dailyquo.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquo.data.Quote
import com.example.dailyquo.repository.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    private val _randomQuote = MutableStateFlow<Quote?>(null)
    val randomQuote = _randomQuote.asStateFlow()

    val allQuotes = repository.getAllQuotes()

    init {
        viewModelScope.launch {
            populateDatabase()
        }
    }

    fun fetchRandomQuote() {
        viewModelScope.launch {
            repository.getRandomQuote().collect {
                _randomQuote.value = it
            }
        }
    }

    fun addQuote(quote: Quote) {
        viewModelScope.launch {
            repository.insertQuote(quote)
        }
    }

    fun deleteQuote(quote: Quote) {
        viewModelScope.launch {
            repository.deleteQuote(quote)
        }
    }

    private suspend fun populateDatabase() {
        if (repository.getQuotesCount() == 0) { // Ensure it only runs if DB is empty
            val defaultQuotes = listOf(
                Quote(text = "The only way to do great work is to love what you do.", author = "Steve Jobs"),
                Quote(text = "In the middle of every difficulty lies opportunity.", author = "Albert Einstein"),
                Quote(text = "Success is not final, failure is not fatal: it is the courage to continue that counts.", author = "Winston Churchill"),
                Quote(text = "Do what you can, with what you have, where you are.", author = "Theodore Roosevelt"),
                Quote(text = "Happiness depends upon ourselves.", author = "Aristotle")
            )
            defaultQuotes.forEach { repository.insertQuote(it) }
        }
    }
}
