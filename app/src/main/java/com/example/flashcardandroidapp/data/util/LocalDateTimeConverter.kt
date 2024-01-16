package com.example.flashcardandroidapp.data.util

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId

class LocalDateTimeConverter {
    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun fromLocalDateTime(lct: LocalDateTime?): Long? {
        return lct?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @TypeConverter
    fun toLocalDateTime(lct: Long?): LocalDateTime? {
        return lct?.let {
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(it),
                ZoneId.systemDefault()
            )
        }
    }
}