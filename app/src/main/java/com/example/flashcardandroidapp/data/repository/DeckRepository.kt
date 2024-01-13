package com.example.flashcardandroidapp.data.repository

import com.example.flashcardandroidapp.data.local.DeckDao
import com.example.flashcardandroidapp.domain.model.deck.Deck
import kotlinx.coroutines.flow.Flow

class DeckRepository(
    private val deckDao: DeckDao
) {

    fun getAllDecks(): Flow<List<Deck>> = deckDao.getAllDeck()

    suspend fun getDeckById(id: Int): Deck = deckDao.getDeckById(id = id)

    suspend fun insertDeck(deck: Deck): Unit = deckDao.insertDeck(deck = deck)

    suspend fun updateDeck(deck: Deck): Unit = deckDao.updateDeck(deck = deck)

    suspend fun deleteDeck(deck: Deck): Unit = deckDao.deleteDeck(deck = deck)
}