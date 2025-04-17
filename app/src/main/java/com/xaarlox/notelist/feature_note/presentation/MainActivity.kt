package com.xaarlox.notelist.feature_note.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xaarlox.notelist.NoteApp
import com.xaarlox.notelist.feature_note.presentation.add_edit_note.AddEditNoteScreen
import com.xaarlox.notelist.feature_note.presentation.add_edit_note.AddEditNoteViewModel
import com.xaarlox.notelist.feature_note.presentation.add_edit_note.AddEditNoteViewModelFactory
import com.xaarlox.notelist.feature_note.presentation.notes.NotesScreen
import com.xaarlox.notelist.feature_note.presentation.notes.NotesViewModelFactory
import com.xaarlox.notelist.feature_note.presentation.util.Screen
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
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.NotesScreen.route
                    ) {
                        composable(route = Screen.NotesScreen.route) {
                            NotesScreen(
                                navController = navController,
                                notesViewModelFactory = notesViewModelFactory
                            )
                        }
                        composable(
                            route = Screen.AddEditNoteScreen.route +
                                    "?noteId={noteId}&noteColor={noteColor}",
                            arguments = listOf(
                                navArgument(
                                    name = "noteId"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                },
                                navArgument(
                                    name = "noteColor"
                                ) {
                                    type = NavType.IntType
                                    defaultValue = -1
                                }
                            )
                        ) { entry ->
                            val color = entry.arguments?.getInt("noteColor") ?: -1
                            val factory = addEditNoteViewModelFactory.create(entry, entry.arguments)
                            val viewModel: AddEditNoteViewModel =
                                viewModel(factory = factory, viewModelStoreOwner = entry)
                            AddEditNoteScreen(
                                navController = navController,
                                noteColor = color,
                                viewModel = viewModel
                            )
                        }
                    }
                }
            }
        }
    }
}