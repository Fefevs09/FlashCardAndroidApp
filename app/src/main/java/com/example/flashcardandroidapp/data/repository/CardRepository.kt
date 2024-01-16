package com.example.flashcardandroidapp.data.repository

import com.example.flashcardandroidapp.data.local.CardDao
import com.example.flashcardandroidapp.domain.model.card.Card
import kotlinx.coroutines.flow.Flow

class CardRepository(
    private val cardDao: CardDao
) {
    fun getAllCardsByDeckId(deckId: Int): Flow<List<Card>> = cardDao.getAllCardsByDeckId(deckId = deckId)

    suspend fun getCardById(id: Int): Card = cardDao.getCardById(id = id)

    suspend fun insertCard(card: Card): Unit = cardDao.insertCard(card = card)

    suspend fun updateCard(card: Card): Unit = cardDao.updateCard(card = card)

    suspend fun deleteCard(card: Card): Unit = cardDao.deleteCard(card = card)
}