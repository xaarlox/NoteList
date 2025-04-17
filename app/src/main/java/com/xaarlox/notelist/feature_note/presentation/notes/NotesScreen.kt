package com.xaarlox.notelist.feature_note.presentation.notes

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xaarlox.notelist.feature_note.presentation.notes.components.NoteItem
import com.xaarlox.notelist.feature_note.presentation.notes.components.OrderSection
import com.xaarlox.notelist.feature_note.presentation.util.Screen
import com.xaarlox.notelist.ui.theme.Pink80
import com.xaarlox.notelist.ui.theme.Purple40
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun NotesScreen(
    navController: NavController, notesViewModelFactory: NotesViewModelFactory
) {
    val viewModel: NotesViewModel = viewModel(factory = notesViewModelFactory)
    val state = viewModel.state.value
    val scaffoldState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val infiniteTransition = rememberInfiniteTransition()
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(animation = tween(3000, easing = LinearEasing))
    )

    val color by infiniteTransition.animateColor(
        initialValue = Purple40,
        targetValue = Pink80,
        animationSpec = infiniteRepeatable(animation = tween(3000), repeatMode = RepeatMode.Reverse)
    )

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.navigate(Screen.AddEditNoteScreen.route)
            },
            containerColor = color,
            modifier = Modifier.rotate(rotation),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add note")
        }
    }, snackbarHost = { SnackbarHost(scaffoldState) }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Your note", style = MaterialTheme.typography.headlineMedium)
                IconButton(onClick = {
                    viewModel.onEvent(NotesEvent.ToggleOrderSection)
                }) {
                    Icon(imageVector = Icons.Default.MoreVert, contentDescription = "Sort")
                }
            }
            AnimatedVisibility(
                visible = state.isOrderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                    noteOrder = state.noteOrder,
                    onOrderChange = {
                        viewModel.onEvent(NotesEvent.Order(it))
                    })
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                itemsIndexed(state.notes, key = { _, note -> note.id!! }) { index, note ->
                    val isVisible = remember(note.id) { mutableStateOf(false) }

                    LaunchedEffect(note.id) {
                        delay(index * 100L)
                        isVisible.value = true
                    }

                    AnimatedVisibility(
                        visible = isVisible.value,
                        enter = slideInVertically(animationSpec = tween(durationMillis = 800)) + fadeIn(
                            animationSpec = tween(800)
                        ),
                        exit = slideOutVertically(animationSpec = tween(durationMillis = 300)) + fadeOut(
                            animationSpec = tween(300)
                        )
                    ) {
                        NoteItem(note = note, modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddEditNoteScreen.route + "?noteId=${note.id}&noteColor=${note.color}"
                                )
                            }, onDeleteClick = {
                            viewModel.onEvent(NotesEvent.DeleteNote(note))
                            scope.launch {
                                val result = scaffoldState.showSnackbar(
                                    message = "Note deleted", actionLabel = "Undo"
                                )
                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(NotesEvent.RestoreNote)
                                }
                            }
                        })
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}