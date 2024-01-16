package com.example.flashcardandroidapp

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.flashcardandroidapp.data.local.AppDatabase
import com.example.flashcardandroidapp.data.local.CardDao
import com.example.flashcardandroidapp.data.local.DeckDao
import com.example.flashcardandroidapp.domain.model.card.Card
import com.example.flashcardandroidapp.domain.model.card.Feedback
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
class CardDaoTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var db: AppDatabase
    private lateinit var deckDao: DeckDao
    private lateinit var cardDao: CardDao

    @Before
    fun setup() {
        // val context = ApplicationProvider.getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java,
        ).allowMainThreadQueries().build()

        deckDao = db.deckDao()
        cardDao = db.cardDao()
    }

    @Test
    fun insertCard_returnTrue() = runBlocking {
        val deck = Deck(1, "Deck 1", LocalDateTime.now())
        deckDao.insertDeck(deck)

        val card1 = Card(
            1,
            "pi value?",
            "3.14",
            LocalDateTime.now(),
            Feedback.NONE,
            LocalDateTime.now(),
            1
        )
        cardDao.insertCard(card1)

        val card2 = Card(
            2,
            "President USA?",
            "Biden",
            LocalDateTime.now(),
            Feedback.NONE,
            LocalDateTime.now(),
            1
        )

        cardDao.insertCard(card2)

        val cards = cardDao.getAllCardsByDeckId(1).first()
        Assert.assertNotNull(cards)

        Assert.assertEquals(2, cards.size)

    }

    @Test
    fun getCardById_returnCard() = runBlocking {
        val deck = Deck(1, "Deck 1", LocalDateTime.now())
        deckDao.insertDeck(deck)

        val card1 = Card(
            1,
            "pi value?",
            "3.14",
            LocalDateTime.now(),
            Feedback.NONE,
            LocalDateTime.now(),
            1
        )
        cardDao.insertCard(card1)

        val card2 = Card(
            2,
            "President USA?",
            "Biden",
            LocalDateTime.now(),
            Feedback.NONE,
            LocalDateTime.now(),
            1
        )
        cardDao.insertCard(card2)

        val correctCard = cardDao.getCardById(card1.id)
        Assert.assertNotNull(correctCard)

        Assert.assertEquals(card1.question, correctCard.question)
        Assert.assertEquals(card1.answer, correctCard.answer)
        Assert.assertEquals(card1.feedback, correctCard.feedback)

    }

    @Test
    fun updateCard_returnUpdatedCard() = runBlocking {
        val deck = Deck(1, "Deck 1", LocalDateTime.now())
        deckDao.insertDeck(deck)

        val card1 = Card(
            1,
            "pi value?",
            "3.14",
            LocalDateTime.now(),
            Feedback.NONE,
            LocalDateTime.now(),
            1
        )
        cardDao.insertCard(card1)

        val newCard = Card(
            1,
            "President USA?",
            "Biden",
            LocalDateTime.now(),
            Feedback.GOOD,
            LocalDateTime.now(),
            1
        )

        cardDao.updateCard(newCard)

        val card = cardDao.getCardById(card1.id)
        Assert.assertNotNull(card)

        Assert.assertNotEquals(Feedback.NONE, card.feedback)
        Assert.assertEquals(Feedback.GOOD, card.feedback)
        Assert.assertNotEquals(card1.question, card.question)
        Assert.assertNotEquals(card1.answer, card.answer)
        Assert.assertEquals(newCard.question, card.question)
        Assert.assertEquals(newCard.answer, card.answer)
    }

    @Test
    fun deleteCard_returnNoneCard() = runBlocking {
        val deck = Deck(1, "Deck 1", LocalDateTime.now())
        deckDao.insertDeck(deck)

        val card1 = Card(
            1,
            "pi value?",
            "3.14",
            LocalDateTime.now(),
            Feedback.NONE,
            LocalDateTime.now(),
            1
        )
        cardDao.insertCard(card1)

        val card2 = Card(
            2,
            "President USA?",
            "Biden",
            LocalDateTime.now(),
            Feedback.GOOD,
            LocalDateTime.now(),
            1
        )
        cardDao.insertCard(card2)

        cardDao.deleteCard(card1)

        val cards = cardDao.getAllCardsByDeckId(1).first()
        Assert.assertNotNull(cards)

        val card = cardDao.getCardById(1)
        Assert.assertNull(card)

        Assert.assertEquals(1, cards.size)
        Assert.assertNotEquals(2, cards.size)
    }

    @Test
    fun deleteDeck_returnNoneCard() = runBlocking {

        val deck = Deck(1, "Deck 1", LocalDateTime.now())
        deckDao.insertDeck(deck)

        val card1 = Card(
            1,
            "pi value?",
            "3.14",
            LocalDateTime.now(),
            Feedback.NONE,
            LocalDateTime.now(),
            1
        )
        cardDao.insertCard(card1)

        val card2 = Card(
            2,
            "President USA?",
            "Biden",
            LocalDateTime.now(),
            Feedback.GOOD,
            LocalDateTime.now(),
            1
        )

        cardDao.insertCard(card2)

        var cards = cardDao.getAllCardsByDeckId(1).first()
        Assert.assertNotNull(cards)
        Assert.assertEquals(2, cards.size)

        deckDao.deleteDeck(deck)

        cards = cardDao.getAllCardsByDeckId(1).first()
        Assert.assertEquals(0 , cards.size)

        val card = cardDao.getCardById(1)
        Assert.assertNull(card)
    }


    @After
    fun tearDown() {
        db.close()
    }
}