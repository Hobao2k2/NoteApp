package com.example.noteappha.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappha.R
import com.example.noteappha.adapter.NoteAdapter
import com.example.noteappha.databinding.ActivityListNoteBinding
import com.example.noteappha.model.Note
import com.example.noteappha.repository.NoteRepository
import com.example.noteappha.viewmodel.NoteViewModel
import com.example.noteappha.viewmodel.NoteViewModelFactory
import kotlinx.coroutines.launch

class ListNote : AppCompatActivity(), NoteAdapter.OnItemClickListener {
    private lateinit var binding: ActivityListNoteBinding
    private val viewModel: NoteViewModel by viewModels { NoteViewModelFactory(NoteRepository()) }
    private val noteAdapter by lazy {
        NoteAdapter(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ListNote)
            adapter = noteAdapter
        }
        viewModel.getNotes()
        lifecycleScope.launch {
            viewModel.listNote.collect {
                noteAdapter.submitList(it)
            }
        }
    }




    override fun onItemClick(note: Note) {
        Log.i("ListNote", "Clicked on note: ${note.title}")
        val intent = Intent(this, NoteDetailActivity::class.java)
        intent.putExtra("id", "${note.id}")
        intent.putExtra("title", note.title)
        intent.putExtra("content", note.content)
        startActivity(intent)
    }
}
