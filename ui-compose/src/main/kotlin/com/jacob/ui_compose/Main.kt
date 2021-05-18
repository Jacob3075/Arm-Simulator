package com.jacob.ui_compose

import androidx.compose.desktop.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun main() = MyApp {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize().size(width = 600.dp, height = 400.dp)
    ) {
        LeftSideBar(
            modifier = Modifier.weight(0.40f, true)
                .fillMaxHeight()
                .background(color = Color.LightGray)
                .padding(16.dp)
        )
        CodeViewer(
            modifier = Modifier
                .fillMaxSize()
                .weight(1.0f, true)
                .background(color = Color.LightGray)
        )
        RightSideBar(
            modifier = Modifier.weight(0.40f, true)
                .fillMaxHeight()
                .background(color = Color.LightGray)
                .padding(16.dp)
        )
    }
}

private fun MyApp(content: @Composable () -> Unit) {
    Window(
        size = IntSize(1200, 800),
    ) {
        MaterialTheme {
            content()
        }
    }
}
