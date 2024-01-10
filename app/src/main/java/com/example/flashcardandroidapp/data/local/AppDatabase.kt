package com.example.flashcardandroidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flashcardandroidapp.domain.model.deck.Deck

@Database(entities = [Deck::class, CardDao::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun deckDao(): DeckDao
    abstract fun cardDao(): CardDao
}