package com.example.flashcardandroidapp.data.remote.entities

data class OpenAIPrompt(
    val prompt: String,
    val model: String = "text-davinci-003",
    val max_tokens: Int = 100,
    val n: Int = 1,
    val temperature: Double = 0.7
)
