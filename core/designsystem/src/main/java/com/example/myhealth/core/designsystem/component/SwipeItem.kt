package com.example.myhealth.core.designsystem.component

import androidx.compose.animation.core.animate
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun SwipeItem(
    enabled: Boolean = true,
    isBackgroundRevealed: Boolean = false,
    onDragStarted: () -> Unit = {},
    onBackgroundHidden: () -> Unit = {},
    onDismiss: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    var backgroundWidthPx by remember { mutableFloatStateOf(0f) }
    var offsetX by remember { mutableFloatStateOf(0f) }

    LaunchedEffect(isBackgroundRevealed) {
        if (!isBackgroundRevealed && offsetX != 0f) {
            animate(
                initialValue = offsetX,
                targetValue = 0f,
            ) { value, _ ->
                offsetX = value
            }
            onBackgroundHidden()
        }
    }

    val draggableState = rememberDraggableState { delta ->
        offsetX = (offsetX + delta).coerceAtMost(0f)
    }

    val progress =
        if (backgroundWidthPx > 0f) {
            (-offsetX / backgroundWidthPx).coerceIn(0f, 1f)
        } else {
            0f
        }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        SwipeItemBackground(
            progress = progress,
            onSizeChanged = { width ->
                backgroundWidthPx = width.toFloat()
            },
            onDismiss = onDismiss,
        )

        Box(
            modifier = Modifier
                .offset { (IntOffset(offsetX.roundToInt(), 0)) }
                .draggable(
                    enabled = enabled,
                    orientation = Orientation.Horizontal,
                    state = draggableState,
                    onDragStarted = { onDragStarted() },
                    onDragStopped = {
                        if (offsetX <= -backgroundWidthPx) {
                            offsetX = -backgroundWidthPx
                        } else {
                            offsetX = 0f
                            onBackgroundHidden()
                        }
                    }
                ),
        ) {
            content()
        }
    }
}

@Composable
fun SwipeItemBackground(
    progress: Float,
    onSizeChanged: (Int) -> Unit,
    onDismiss: () -> Unit,
) {
    MHDismissButton(
        modifier = Modifier
            .onSizeChanged { size ->
                onSizeChanged(size.width)
            }
            .graphicsLayer {
                scaleX = progress
                scaleY = progress
            },
        onClick = onDismiss,
    )
}