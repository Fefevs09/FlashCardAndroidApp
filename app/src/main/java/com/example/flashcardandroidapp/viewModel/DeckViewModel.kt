package com.example.flashcardandroidapp.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardandroidapp.data.repository.DeckRepository
import com.example.flashcardandroidapp.domain.model.deck.Deck
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class DeckViewModel @Inject constructor(
    private val repository: DeckRepository
): ViewModel() {

    var deck by mutableStateOf<Deck?>(null)
        private set

    val getAllDecks = repository.getAllDecks()

    private var deletedDeck: Deck? = null

    fun getDeckById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            deck = repository.getDeckById(id = id)
        }
    }

    fun insertDeck(deck: Deck) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertDeck(deck)
        }
    }

    fun updateDeck(deck: Deck) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDeck(deck)
        }
    }

    fun deleteDeck(deck: Deck) {
        viewModelScope.launch(Dispatchers.IO) {
            deletedDeck = deck
            repository.deleteDeck(deck)
        }
    }

    fun undoDeletedDeck() {
        deletedDeck?.let { deck ->
            viewModelScope.launch(Dispatchers.IO) {
                repository.insertDeck(deck)
            }
        }
    }

    fun updateTitle(newTitle: String) {
        deck = deck?.copy(title = newTitle)
    }
}