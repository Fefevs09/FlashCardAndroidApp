package com.example.flashcardandroidapp.domain.model.deck

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "Deck"
)
data class Deck (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "create_data")
    val createData: LocalDateTime
    //val UserID: Int
)