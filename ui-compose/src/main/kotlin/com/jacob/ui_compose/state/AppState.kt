package com.jacob.ui_compose.state

import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.Parser
import com.jacob.core_lib.parser.data.ParsedData
import com.jacob.ui_compose.emptyCore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File

class AppState {
    val fileFlow = MutableStateFlow(File(""))
    private val _coreFlow: MutableStateFlow<Core> = MutableStateFlow(emptyCore())
    val coreFlow: StateFlow<Core>
        get() = _coreFlow.asStateFlow()

    private fun createCore() {
        val (instructions: List<Instruction>, parsedVariableData: List<ParsedData>) = Parser.parseDataFromFile(fileFlow.value)
        val program = Program(
            instructions = instructions,
            parsedData = parsedVariableData
        )
        _coreFlow.value = Core(program = program)
    }

    val loadFile: (File) -> Unit = { newFile: File ->
        fileFlow.value = newFile
        createCore()
    }

    val executeProgram: () -> Unit = {
        println("Called")
        println(_coreFlow.value)
        _coreFlow.value.runProgram()
        println(_coreFlow.value.memoryArray.mainMemory.size)
    }
}
