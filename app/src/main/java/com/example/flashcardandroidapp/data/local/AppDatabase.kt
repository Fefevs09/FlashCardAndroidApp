package com.example.flashcardandroidapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.flashcardandroidapp.data.util.LocalDateTimeConverter
import com.example.flashcardandroidapp.domain.model.card.Card
import com.example.flashcardandroidapp.domain.model.deck.Deck

@TypeConverters(LocalDateTimeConverter::class)
@Database(entities = [Deck::class, Card::class], version = 1, exportSchema = true)
abstract class AppDatabase: RoomDatabase() {
    abstract fun deckDao(): DeckDao
    abstract fun cardDao(): CardDao
}