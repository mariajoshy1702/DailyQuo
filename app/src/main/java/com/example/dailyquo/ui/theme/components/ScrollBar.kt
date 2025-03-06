package com.example.dailyquo.ui.theme.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ScrollBar(
    lazyListState: LazyListState
) {
    val viewportHeight = remember { derivedStateOf { lazyListState.layoutInfo.viewportSize.height } }
    val totalItemCount = remember { derivedStateOf { lazyListState.layoutInfo.totalItemsCount } }
    val visibleItemCount = remember { derivedStateOf { lazyListState.layoutInfo.visibleItemsInfo.size } }

    val scrollbarHeight = remember(viewportHeight.value, totalItemCount.value, visibleItemCount.value) {
        if (totalItemCount.value > 0) {
            (visibleItemCount.value.toFloat() / totalItemCount.value) * viewportHeight.value
        } else {
            viewportHeight.value.toFloat() / 2
        }
    }

    val color = MaterialTheme.colorScheme.secondary

    val topOffset by remember(lazyListState) {
        derivedStateOf {
            val firstVisibleIndex = lazyListState.firstVisibleItemIndex
            val itemHeight = viewportHeight.value.toFloat() / totalItemCount.value
            val firstItemOffset = lazyListState.firstVisibleItemScrollOffset.toFloat() / viewportHeight.value * itemHeight
            (firstVisibleIndex * itemHeight) + firstItemOffset
        }
    }

    val scope = rememberCoroutineScope()
    var isShown by remember { mutableStateOf(true) }

    var disposeJob: Job? by remember { mutableStateOf(null) }
    DisposableEffect(topOffset) {
        isShown = true
        disposeJob?.cancel()
        disposeJob = scope.launch {
            delay(1000)
            isShown = false
        }
        onDispose { disposeJob?.cancel() }
    }

    AnimatedVisibility(
        visible = isShown,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawRoundRect(
                color = color,
                topLeft = Offset(size.width - 15f, topOffset),
                size = Size(15f, scrollbarHeight),
                cornerRadius = CornerRadius(8f, 8f)
            )
        }
    }
}
