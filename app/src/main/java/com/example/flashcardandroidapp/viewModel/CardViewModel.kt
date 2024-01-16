package com.example.flashcardandroidapp.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardandroidapp.data.repository.CardRepository
import com.example.flashcardandroidapp.domain.model.card.Card
import com.example.flashcardandroidapp.domain.model.card.Feedback
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val repository: CardRepository
) : ViewModel() {

//    var card by mutableStateOf(Card(0,"", "", LocalDateTime.now() , Feedback.NONE, LocalDateTime.now(), 0))
//        private set

    var card by mutableStateOf<Card?>(null)

    var cards by mutableStateOf<List<Card>>(emptyList())
        private set

    private var deletedCard: Card? = null

    fun getAllCardsById(deckId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAllCardsByDeckId(deckId).collect { card ->
                cards = card
            }
        }
    }

    fun getCardById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            card = repository.getCardById(id)
        }
    }

    fun updateCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateCard(card)
        }
    }

    fun insertCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCard(card)
        }
    }

    fun deleteCard(card: Card) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertCard(card)
        }
    }

    fun undoDeletedDeck() {
        deletedCard?.let { card ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.insertCard(card)
            }
        }
    }

    // Update Feedback
    fun updateFeedback(newFeedback: Feedback) {
        card = card?.copy(feedback = newFeedback)
    }
}