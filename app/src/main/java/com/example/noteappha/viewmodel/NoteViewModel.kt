package com.example.noteappha.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.noteappha.model.Note
import com.example.noteappha.repository.NoteRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class NoteViewModel(private val noteRepository: NoteRepository) : ViewModel() {
    private val _listNote = MutableStateFlow<List<Note>>(mutableListOf())
    val listNote = _listNote.asStateFlow()

    fun getNotes() {
        viewModelScope.launch {
            try {
                _listNote.value = noteRepository.getNotes()
            } catch (e: Exception) {
                Log.i("test", e.toString())
            }
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            try {
                val createdNote = noteRepository.createNote(note)
                _listNote.value = _listNote.value.toMutableList().apply {
                    add(createdNote)
                }
            } catch (e: Exception) {
                Log.i("test", e.toString())
            }
        }
    }
}