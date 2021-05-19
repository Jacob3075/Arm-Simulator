package com.jacob.ui_compose

import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import java.io.File

fun main() = MyApp {
    val file =
        remember { mutableStateOf(File("/home/jacob/PES/02 CS252 MPCA/Lab/Week 4/Programs/Week4_Program5_PES2UG19CS902.s")) }
    val setFile = { newFile: File -> file.value = newFile }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.fillMaxSize().size(width = 600.dp, height = 400.dp)
    ) {
        LeftSideBar(
            modifier = Modifier.weight(0.40f, true)
                .fillMaxHeight()
//                .background(color = Color.LightGray)
                .padding(16.dp),
            onClick = setFile
        )
        CodeViewer(
            modifier = Modifier
                .fillMaxSize()
//                .background(color = Color.LightGray)
                .weight(1.0f, true),
            file.value
        )
        RightSideBar(
            modifier = Modifier.weight(0.40f, true)
                .fillMaxHeight()
//                .background(color = Color.LightGray)
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
