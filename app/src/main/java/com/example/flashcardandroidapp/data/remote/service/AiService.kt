package com.example.flashcardandroidapp.data.remote.service

import com.example.flashcardandroidapp.data.remote.entities.OpenAIResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AiService {

    @POST("v1/completions")
    suspend fun completions(@Body openAIResponse: OpenAIResponse): Response<OpenAIResponse>
}