package com.ioki.progressbutton.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.ioki.progressbutton.ProgressButton
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlin.time.Duration.Companion.seconds

class SampleActivity : ComponentActivity() {

    private val logFlow = MutableStateFlow("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Column {
                Column {
                    NormalProgressButton(logFlow)
                    DisabledProgressButton(logFlow)
                    DisabledAfterFinishedProgressButton(logFlow)
                    DifferedSizeProgressButton(logFlow)
                    RoundProgressButton(logFlow)
                    DifferentColorProgressButton(logFlow)
                }
                Divider()
                val scrollState = rememberScrollState()
                Column(
                    modifier = Modifier.verticalScroll(scrollState)
                ) {
                    val state = logFlow.collectAsState("")
                    Text(state.value)
                }
            }
        }
    }
}

@Composable
private fun NormalProgressButton(logFlow: MutableStateFlow<String>) {
    val methodName = object {}.javaClass.enclosingMethod?.name ?: ""

    ProgressButton(
        modifier = Modifier,
        startDelay = 1.seconds,
        duration = 6.seconds,
        onClick = {
            logFlow.update { "$it \n$methodName Clicked" }
        },
        onFinished = {
            logFlow.update { "$it \n$methodName Finished" }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(start = 26.dp, end = 26.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(methodName)
        }
    }
}

@Composable
private fun DisabledProgressButton(logFlow: MutableStateFlow<String>) {
    val methodName = object {}.javaClass.enclosingMethod?.name ?: ""

    ProgressButton(
        modifier = Modifier,
        startDelay = 2.seconds,
        duration = 8.seconds,
        enabled = false,
        contentColorDisabled = Color.Red,
        onClick = {
            logFlow.update { "$it \n$methodName Clicked" }
        },
        onFinished = {
            logFlow.update { "$it \n$methodName Finished" }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(start = 26.dp, end = 26.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("$methodName - Do not touch!")
        }
    }
}

@Composable
private fun DisabledAfterFinishedProgressButton(logFlow: MutableStateFlow<String>) {
    val methodName = object {}.javaClass.enclosingMethod?.name ?: ""

    var enabled by remember { mutableStateOf(true) }
    ProgressButton(
        modifier = Modifier,
        startDelay = 3.seconds,
        duration = 7.seconds,
        enabled = enabled,
        onClick = {
            logFlow.update { "$it \n$methodName Clicked" }
        },
        onFinished = {
            enabled = false
            logFlow.update { "$it \n$methodName Finished" }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(start = 26.dp, end = 26.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(methodName)
        }
    }
}

@Composable
private fun DifferedSizeProgressButton(logFlow: MutableStateFlow<String>) {
    val methodName = object {}.javaClass.enclosingMethod?.name ?: ""

    ProgressButton(
        modifier = Modifier.size(height = 80.dp, width = 450.dp),
        startDelay = 4.seconds,
        duration = 5.seconds,
        onClick = {
            logFlow.update { "$it \n$methodName Clicked" }
        },
        onFinished = {
            logFlow.update { "$it \n$methodName Finished" }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(start = 26.dp, end = 26.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(methodName)
        }
    }
}

@Composable
private fun RoundProgressButton(logFlow: MutableStateFlow<String>) {
    val methodName = object {}.javaClass.enclosingMethod?.name ?: ""

    ProgressButton(
        modifier = Modifier.size(height = 240.dp, width = 239.dp),
        duration = 16.seconds,
        onClick = {
            logFlow.update { "$it \n$methodName Clicked" }
        },
        onFinished = {
            logFlow.update { "$it \n$methodName Finished" }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(start = 26.dp, end = 26.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(methodName)
        }
    }
}

@Composable
private fun DifferentColorProgressButton(logFlow: MutableStateFlow<String>) {
    val methodName = object {}.javaClass.enclosingMethod?.name ?: ""

    ProgressButton(
        modifier = Modifier,
        duration = 10.seconds,
        backgroundColor = colorResource(id = R.color.teal_700),
        progressColor = colorResource(id = R.color.teal_200),
        onClick = {
            logFlow.update { "$it \n$methodName Clicked" }
        },
        onFinished = {
            logFlow.update { "$it \n$methodName Finished" }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(start = 26.dp, end = 26.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(methodName)
        }
    }
}