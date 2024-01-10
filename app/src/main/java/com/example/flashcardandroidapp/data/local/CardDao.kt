package com.example.flashcardandroidapp.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.flashcardandroidapp.domain.model.card.Card
import kotlinx.coroutines.flow.Flow

interface CardDao {

//    @Query("SELECT * FROM Card")
//    fun getAllCard(): Flow<List<Card>>

    @Query("SELECT * FROM Card WHERE deck_id = :deckId")
    fun getAllCardsById(deckId: Int): Flow<List<Card>>

    @Query("SELECT * FROM Card WHERE id= :id")
    suspend fun getCardById(id: Int): Card

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: Card)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateCard(card: Card)

    @Delete
    suspend fun deleteCard(card: Card)
}