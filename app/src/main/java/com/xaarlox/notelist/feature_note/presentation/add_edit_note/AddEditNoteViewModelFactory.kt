package com.xaarlox.notelist.feature_note.presentation.add_edit_note

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.xaarlox.notelist.feature_note.domain.use_case.NoteUseCases
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class AddEditNoteViewModelFactory @AssistedInject constructor(
    private val noteUseCases: NoteUseCases,
    @Assisted private val owner: SavedStateRegistryOwner,
    @Assisted private val defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return AddEditNoteViewModel(noteUseCases = noteUseCases, savedStateHandle = handle) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle?
        ): AddEditNoteViewModelFactory
    }
}