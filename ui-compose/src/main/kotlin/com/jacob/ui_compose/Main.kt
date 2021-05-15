package com.jacob.ui_compose

import androidx.compose.desktop.Window
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.parser.Parser
import java.io.File

fun main() = Window {
    val file = File("./InputFile.txt")
    val dataFromFile = Parser.parseDataFromFile(file)
    val variables = dataFromFile.second

    val core = Core(program = Program(dataFromFile.first, variables))
    core.runProgram()

    println(core.registerArray)
    println(core.memoryArray)

    var text by remember { mutableStateOf("Hello, World!") }

    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}
