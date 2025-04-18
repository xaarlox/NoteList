package com.xaarlox.notelist.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.xaarlox.notelist.feature_note.domain.use_case.NoteUseCases
import javax.inject.Inject

class NotesViewModelFactory @Inject constructor(
    private val noteUseCases: NoteUseCases
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            return NotesViewModel(noteUseCases) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}