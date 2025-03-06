package com.example.dailyquo.ui.theme.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp

@Composable
fun GetLeftCanvas(){
    val bubbleColor = MaterialTheme.colorScheme.secondary
    Canvas(
        modifier = Modifier
            .size(30.dp)
            .offset(x = (-10).dp, y = 8.dp)
    ) {
        scale(scaleX = -1f, scaleY = 1f) {
            val path = Path().apply {
                moveTo(size.width, 0f)
                lineTo(0f, size.height)
                lineTo(0f, 0f)
                close()
            }
            drawPath(path, color = bubbleColor)
        }
    }
}

@Composable
fun GetRightCanvas() {
    val bubbleColor = MaterialTheme.colorScheme.secondary

    Canvas(
        modifier = Modifier
            .size(30.dp)
            .offset(x = 0.dp, y = (-8).dp)
    ) {
        val path = Path().apply {
            moveTo(size.width, size.height)
            lineTo(0f, 0f)
            lineTo(0f, size.height)
            close()
        }
        drawPath(path, color = bubbleColor)
    }
}

