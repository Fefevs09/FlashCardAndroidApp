package com.example.flashcardandroidapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.flashcardandroidapp.data.local.AppDatabase
import com.example.flashcardandroidapp.data.local.DeckDao
import com.example.flashcardandroidapp.domain.model.deck.Deck
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class DeckDaoTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var deckDao: DeckDao

    @Before
    fun setup() {
        // val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).allowMainThreadQueries().build()

        deckDao = db.deckDao()
    }

    @Test
    fun insertDecks_returnDecksSize() = runBlocking {
        val deck1 = Deck(0, "Deck 1", LocalDateTime.now())
        val deck2 = Deck(0, "Deck 2", LocalDateTime.now())

        deckDao.insertDeck(deck1)
        deckDao.insertDeck(deck2)

        val decks = deckDao.getAllDeck().first()

        Assert.assertEquals(2, decks.size)
    }

    @Test
    fun deleteDeck_returnTrue() = runBlocking {
        val deck1 = Deck(0, "Deck 1", LocalDateTime.now())
        val deck2 = Deck(0, "Deck 2", LocalDateTime.now())

        deckDao.insertDeck(deck1)
        deckDao.insertDeck(deck2)

        deckDao.deleteDeck(deck2)

        val decks: List<Deck> = deckDao.getAllDeck().first()
        Log.d("DeckDaoTest", "Contents of decks: $decks")

        Assert.assertEquals("Deck 1", decks[0].title)
    }

    @Test
    fun updateDeck_returnDeckUpdated() = runBlocking {
        val deck = Deck(1, "Deck 1", LocalDateTime.now())

        deckDao.insertDeck(deck)

        val updatedDeck = Deck(1, "Test", LocalDateTime.now())

        deckDao.updateDeck(updatedDeck)

        val decks: List<Deck> = deckDao.getAllDeck().first()
        Log.d("DeckDaoTest", "Contents of decks: $decks")

        Assert.assertEquals("Test", decks[0].title)
        Assert.assertNotEquals(deck.title, decks[0].title)
    }

    @Test
    fun getDeckById_returnTrue() = runBlocking {
        val deck1 = Deck(1, "Deck 1", LocalDateTime.now())
        val deck2 = Deck(0, "Deck 1", LocalDateTime.now())

        deckDao.insertDeck(deck1)
        deckDao.insertDeck(deck2)

//        val decks: List<Deck> = deckDao.getAllDeck().first()
//        Log.d("DeckDaoTest", "Contents of decks: $decks")
        val getDeck1 = deckDao.getDeckById(1)

        Assert.assertEquals(deck1, getDeck1)
    }

    @After
    fun tearDown() {
        db.close()
    }


}