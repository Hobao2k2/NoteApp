package com.example.noteappha.network

import com.example.noteappha.model.Note
import retrofit2.Call
import retrofit2.http.*

interface NoteApi {

    @GET("notes")
    suspend fun getNotes(): List<Note>

    @POST("notes")
    suspend fun createNote(@Body note: Note): Note

    @GET("notes/{id}")
    suspend fun getNoteById(@Path("id") id: Long): Note

    @PUT("notes/{id}")
    suspend fun updateNote(@Path("id") id: Long, @Body note: Note): Note

    @DELETE("notes/{id}")
    suspend fun deleteNote(@Path("id") id: Long)
}
