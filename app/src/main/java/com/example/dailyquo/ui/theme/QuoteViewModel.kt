package com.example.dailyquo.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquo.data.room.Quote
import com.example.dailyquo.repository.QuoteRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    private val _randomQuote = MutableStateFlow<Quote?>(null)
    val randomQuote = _randomQuote.asStateFlow()

    val allQuotes = repository.getAllQuotes()

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
}
