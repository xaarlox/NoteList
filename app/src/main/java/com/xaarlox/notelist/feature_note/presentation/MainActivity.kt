package com.xaarlox.notelist.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.xaarlox.notelist.NoteApp
import com.xaarlox.notelist.feature_note.presentation.add_edit_note.AddEditNoteViewModelFactory
import com.xaarlox.notelist.feature_note.presentation.notes.NotesViewModelFactory
import com.xaarlox.notelist.feature_note.presentation.util.NoteNavHost
import com.xaarlox.notelist.ui.theme.NoteListTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var notesViewModelFactory: NotesViewModelFactory

    @Inject
    lateinit var addEditNoteViewModelFactory: AddEditNoteViewModelFactory.Factory

    @ExperimentalMaterial3Api
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as NoteApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NoteListTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .navigationBarsPadding(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NoteNavHost(
                        notesViewModelFactory = notesViewModelFactory,
                        addEditNoteViewModelFactory = addEditNoteViewModelFactory
                    )
                }
            }
        }
    }
}