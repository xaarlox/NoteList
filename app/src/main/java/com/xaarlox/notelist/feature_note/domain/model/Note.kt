package com.xaarlox.notelist.feature_note.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xaarlox.notelist.ui.theme.BabyBlue
import com.xaarlox.notelist.ui.theme.LightGreen
import com.xaarlox.notelist.ui.theme.RedOrange
import com.xaarlox.notelist.ui.theme.RedPink
import com.xaarlox.notelist.ui.theme.Violet

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val title: String,
    val content: String,
    val date: Long = System.currentTimeMillis(),
    val color: Int
) {
    companion object {
        val noteColors = listOf(RedOrange, LightGreen, Violet, BabyBlue, RedPink)
    }
}

class InvalidNoteException(message: String) : Exception(message)