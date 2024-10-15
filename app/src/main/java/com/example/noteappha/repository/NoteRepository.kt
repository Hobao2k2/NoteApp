package com.example.noteappha.repository

import android.util.Log
import com.example.noteappha.model.Note
import com.example.noteappha.network.RetrofitInstance

class NoteRepository {
    private val api=RetrofitInstance.getRetrofitClient()

    suspend fun getNotes():List<Note>{
        return api.getNotes()
    }
    suspend fun createNote(note:Note):Note{
        return api.createNote(note)
    }
}