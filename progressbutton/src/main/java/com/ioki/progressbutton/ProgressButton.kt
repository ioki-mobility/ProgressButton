package com.ioki.progressbutton

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

@Composable
fun ProgressButton(
    modifier: Modifier,
    backgroundColor: Color = Color.Transparent,
    progressColor: Color = MaterialTheme.colors.primary,
    contentColor: Color = contentColorFor(progressColor),
    contentColorDisabled: Color = MaterialTheme.colors.primaryVariant,
    enabled: Boolean = true,
    startDelay: Duration = Duration.ZERO,
    duration: Duration = 10L.seconds,
    restDuration: Duration = duration,
    onClick: () -> Unit = {},
    onFinished: () -> Unit = {},
    content: @Composable () -> Unit
) {
    FloatingActionButton(
        modifier = modifier
            .width(IntrinsicSize.Max)
            .sizeIn(
                minWidth = 116.dp,
                minHeight = 36.dp,
                maxHeight = 36.dp
            )
            .clickable(
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            ),
        onClick = {
            if (enabled) onClick()
        },
        backgroundColor = backgroundColor,
        contentColor = if (enabled) contentColor else contentColorDisabled,
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 2.dp,
            pressedElevation = 2.dp
        )
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
        ) {
            var progress by remember {
                val startProgress = calculateStartProgressInPercent(
                    duration = duration,
                    restDuration = restDuration
                )
                mutableStateOf(startProgress)
            }
            val animatedProgress: Float by animateFloatAsState(
                targetValue = progress,
                animationSpec = tween(
                    durationMillis = restDuration.inWholeMilliseconds.toInt(),
                    delayMillis = startDelay.inWholeMilliseconds.toInt() ,
                    easing = LinearEasing,
                ),
                finishedListener = {
                    onFinished()
                }
            )

            LaunchedEffect(key1 = Unit) {
                progress = 0f
            }

            Box(
                Modifier
                    .fillMaxWidth(animatedProgress)
                    .fillMaxHeight()
                    .background(progressColor)
            )

            content()
        }
    }
}

private fun calculateStartProgressInPercent(
    duration: Duration,
    restDuration: Duration
): Float =
    (100 * restDuration.inWholeMilliseconds.toFloat() / duration.inWholeMilliseconds.toFloat()) / 100
