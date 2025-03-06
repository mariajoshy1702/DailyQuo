package com.example.dailyquo.ui.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailyquo.data.room.Quote
import com.example.dailyquo.repository.QuoteRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    // Holds the list of saved quotes from the database
    private val _savedQuotes = MutableStateFlow<List<Quote>>(emptyList())
    val savedQuotes = _savedQuotes.asStateFlow()

    // Holds a randomly fetched quote
    private val _randomQuote = MutableStateFlow<Quote?>(null)
    val randomQuote = _randomQuote.asStateFlow()

    // Holds error state (null when no error, string message when error occurs)
    private var _errorState = MutableStateFlow<String?>(null)
    var errorState = _errorState.asStateFlow()

    // Stores the timestamp of the last button click to prevent rapid consecutive clicks
    private var lastClickTime: Long = 0
    private val debounceDelay: Long = 1000

    // Holds the countdown timer value
    private val _timeLeft = MutableStateFlow(0)
    val timeLeft = _timeLeft.asStateFlow()

    // Prevents multiple countdowns from running at the same time
    private var isCountdownActive = false

    // Fetch all saved quotes from the database and update _savedQuotes state
    init {
        viewModelScope.launch {
            repository.getAllQuotes().collect { quotes ->
                _savedQuotes.value = quotes
            }
        }
    }

    /**
     * Function to add a new quote to the database.
     * Used in AddQuotes screen and from the Home screen's IconButton.
     */
    fun addQuote(quote: Quote) {
        viewModelScope.launch {
            repository.insertQuote(quote)
        }
    }

    /**
     * Function to delete a quote from the database.
     * Used in the AddQuotes screen.
     */
    fun deleteQuote(quote: Quote) {
        viewModelScope.launch {
            repository.deleteQuote(quote)
        }
    }

    /**
     * Function to delete a quote by its text content.
     * Used when deleting quotes from the Home screen via the Toggle IconButton.
     */
    fun deleteQuoteByText(quote: Quote) {
        viewModelScope.launch {
            repository.deleteQuoteByText(quote.text)
        }
    }

    /**
     * Function to fetch a random quote when the app opens
     * & when user clicks the new quote button.
     * Uses a debounce mechanism to prevent rapid clicks causing excessive API calls.
     * Updates `_randomQuote` state with the fetched quote.
     */
    fun fetchRandomQuote() {
        val currentClickTime = System.currentTimeMillis()
        if (currentClickTime - lastClickTime < debounceDelay) {
            return
        }
        lastClickTime = currentClickTime
        viewModelScope.launch {
            try {
                repository.getRandomQuote().collect {
                    _randomQuote.value = it
                    _errorState.value = null
                }
            } catch (e: retrofit2.HttpException) {
                handleError()
            } catch (e: Exception) {
                handleError()
            }
        }
    }

    /**
     * Function to start a countdown timer from 28 seconds when an error occurs.
     * Ensures only one countdown is active at a time.
     */
    fun startCountdownExternally() {
        if (!isCountdownActive) {
            isCountdownActive = true
            _timeLeft.value = 28
            viewModelScope.launch {
                startCountdown()
                isCountdownActive = false
            }
        }
    }

    /**
     * Function to run the countdown timer, decreasing timeLeft
     * by 1 every second until it reaches 0.
     */
    private suspend fun startCountdown() {
        while (_timeLeft.value > 0) {
            delay(1000L)
            _timeLeft.value -= 1
        }
    }

    /**
     * Function to handle errors by setting the error state,
     * starting a countdown timer, and resetting the error after 30 seconds.
     */
    private suspend fun handleError() {
        _errorState.value = "error"
        startCountdown()
        delay(30000)
        _errorState.value = null
    }
}
