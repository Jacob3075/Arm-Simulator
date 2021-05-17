package com.jacob.ui_compose

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.jacob.ui_compose.left_sidebar.LeftSideBar
import com.jacob.ui_compose.right_sidebar.RightSideBar

fun main() = MyApp {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize().size(width = 600.dp, height = 400.dp)
    ) {
        LeftSideBar(Modifier.weight(0.40f, true))
        CodeViewer(Modifier.weight(1.0f, true))
        RightSideBar(Modifier.weight(0.40f, true))
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
