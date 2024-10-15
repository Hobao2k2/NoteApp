package com.example.noteappha.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.example.noteappha.R
import com.example.noteappha.databinding.ActivityAddNoteBinding
import com.example.noteappha.model.Note
import com.example.noteappha.repository.NoteRepository
import com.example.noteappha.viewmodel.NoteViewModel
import com.example.noteappha.viewmodel.NoteViewModelFactory
import kotlinx.coroutines.launch

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddNoteBinding
    private val viewModel: NoteViewModel by viewModels { NoteViewModelFactory(NoteRepository()) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.btnAdd.setOnClickListener {
            val note=Note(null,binding.edtTitle.text.toString(),binding.edtDescription.text.toString())
            lifecycleScope.launch {
                viewModel.addNote(note)
                    val intent = Intent(this@AddNoteActivity, ListNote::class.java)
                    startActivity(intent)
            }
        }
    }
}