package com.jacob.ui_compose

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.jacob.ui_compose.state.AppState

fun main() = MyApp {
    val appState = remember { AppState() }
    val fileState = appState.fileFlow.collectAsState()
    val memoryArrayState = appState.memoryArrayFlow.collectAsState()
    val registerArrayState = appState.registerArrayFlow.collectAsState()

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize().size(width = 600.dp, height = 400.dp)
    ) {
        LeftSideBar(
            modifier = Modifier.weight(0.40f, true)
                .fillMaxHeight()
                .padding(16.dp),
            memoryArray = memoryArrayState.value,
            loadFile = appState.loadFile,
            fileName = fileState.value.name,
            executeProgram = appState.executeProgram,
            executeNextInstruction = appState.executeNextInstruction,
            resetProgram = appState.resetProgram
        )
        CodeViewer(
            modifier = Modifier
                .fillMaxSize()
                .weight(1.0f, true),
            file = fileState.value
        )
        RightSideBar(
            modifier = Modifier.weight(0.40f, true)
                .fillMaxHeight()
                .padding(16.dp),
            registersArray = registerArrayState.value
        )
    }
}

private fun MyApp(content: @Composable () -> Unit) {
    Window(
        size = IntSize(1200, 850),
    ) {
        MaterialTheme {
            content()
        }
    }
}

// TODO: CHANGE BACKGROUND COLOR OF ITEMS THAT HAVE CHANGED
