package com.xaarlox.notelist.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
    val date: Long = System.currentTimeMillis()
)

class InvalidNoteException(message: String) : Exception(message)