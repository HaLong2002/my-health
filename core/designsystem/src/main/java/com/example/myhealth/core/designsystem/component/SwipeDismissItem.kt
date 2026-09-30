package com.example.myhealth.core.designsystem.component

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

@Composable
fun SwipeDismissItem(
    enabled: Boolean = true,
    onDragItem: (Boolean) -> Unit = {},
    onDismiss: () -> Unit,
    content: @Composable () -> Unit,
) {
    var deleteWidthPx by remember { mutableFloatStateOf(0f) }

    var offsetX by remember { mutableFloatStateOf(0f) }
    var isRevealed by remember { mutableStateOf(false) }

    val dismissThreshold = -deleteWidthPx * 4f

    val draggableState = rememberDraggableState { delta ->
        offsetX = (offsetX + delta).coerceAtMost(0f)
    }

    val progress =
        if (deleteWidthPx > 0f) {
            (-offsetX / deleteWidthPx).coerceIn(0f, 1f)
        } else {
            0f
        }

    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        SwipeDismissItemBackground(
            progress = progress,
            onSizeChanged = { width ->
                deleteWidthPx = width.toFloat()
            }
        )

        Box(
            modifier = Modifier
                .offset { (IntOffset(offsetX.roundToInt(), 0)) }
                .draggable(
                    enabled = enabled,
                    orientation = Orientation.Horizontal,
                    state = draggableState,
                    onDragStopped = {
                        if (!isRevealed) {
                            if (offsetX <= -deleteWidthPx) {
                                offsetX = -deleteWidthPx
                                isRevealed = true
                            } else {
                                offsetX = 0f
                            }
                        } else {
                            when {
                                offsetX <= dismissThreshold -> {
                                    onDismiss()
                                }
                                offsetX > -deleteWidthPx -> {
                                    offsetX = 0f
                                    isRevealed = false
                                }
                                else -> {
                                    offsetX = -deleteWidthPx
                                }
                            }
                        }
                    }
                ),
        ) {
            content()
        }
    }
}

@Composable
fun SwipeDismissItemBackground(
    progress: Float,
    onSizeChanged: (Int) -> Unit,
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
        onClick = {},
    )
}