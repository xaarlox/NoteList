package com.xaarlox.notelist.feature_note.presentation.notes.components

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xaarlox.notelist.feature_note.domain.model.Note

@Composable
fun NoteItem(
    note: Note, modifier: Modifier = Modifier, cornerRadius: Dp = 10.dp, onDeleteClick: () -> Unit
) {

    val infiniteTransition = rememberInfiniteTransition()
    val translationY by infiniteTransition.animateFloat(
        initialValue = 0f, targetValue = -4f, animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 500, easing = FastOutSlowInEasing
            ), repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(note.color), shape = RoundedCornerShape(cornerRadius)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = note.title,
                modifier = Modifier.weight(1f),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(
                onClick = onDeleteClick, modifier = Modifier.offset(y = translationY.dp)
            ) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete note")
            }
        }
    }
}