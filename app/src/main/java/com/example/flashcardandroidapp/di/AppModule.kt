package com.example.flashcardandroidapp.di

import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.flashcardandroidapp.data.local.AppDatabase
import com.example.flashcardandroidapp.data.local.CardDao
import com.example.flashcardandroidapp.data.local.DeckDao
import com.example.flashcardandroidapp.data.repository.CardRepository
import com.example.flashcardandroidapp.data.repository.DeckRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "flash_card_db"
        ).build()
    }


    @Provides
    @Singleton
    fun provideDeckDao(db: AppDatabase): DeckDao = db.deckDao()

    @Provides
    @Singleton
    fun provideCardDao(db: AppDatabase): CardDao = db.cardDao()

    @Provides
    @Singleton
    fun provideDeckRepository(dao: DeckDao): DeckRepository = DeckRepository(deckDao = dao)

    @Provides
    @Singleton
    fun provideCardRepository(dao: CardDao): CardRepository = CardRepository(cardDao = dao)

}