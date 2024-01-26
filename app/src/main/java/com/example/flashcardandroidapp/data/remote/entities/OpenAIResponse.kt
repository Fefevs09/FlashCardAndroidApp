package com.example.flashcardandroidapp.data.remote.entities

data class OpenAIResponse(
    val id: Int,
    val choices: List<OpenAIChoice>
)
