package com.example.flashcardandroidapp.domain.model.card

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.flashcardandroidapp.domain.model.deck.Deck
import java.time.LocalDateTime

@Entity(tableName = "Card", foreignKeys = [ForeignKey(entity = Deck::class, parentColumns = ["id"], childColumns = ["deckId"], onDelete = ForeignKey.CASCADE)])
data class Card(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "question")
    val question: String,
    @ColumnInfo(name = "answer")
    val answer: String,
    @ColumnInfo(name = "create_data")
    val createData: LocalDateTime,
    @ColumnInfo(name = "feedback")
    val feedback: Feedback,
    @ColumnInfo(name = "review_data")
    val reviewData: LocalDateTime,
    @ColumnInfo(name = "deck_id")
    val deckId: Int
)
