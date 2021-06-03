package com.jacob.ui_compose.state

import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.ParsedData
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.instructions.Instruction
import com.jacob.core_lib.parser.Parser
import com.jacob.ui_compose.convertToMemoryVales
import com.jacob.ui_compose.convertToRegisters
import com.jacob.ui_compose.emptyCore
import com.jacob.ui_compose.models.MemoryValue
import com.jacob.ui_compose.models.RegisterValue
import kotlinx.coroutines.flow.MutableStateFlow
import java.io.File

class AppState {
    val fileFlow = MutableStateFlow(File(""))
    private val _coreFlow: MutableStateFlow<Core> = MutableStateFlow(emptyCore())

    val memoryArrayFlow: MutableStateFlow<List<MemoryValue>> =
        MutableStateFlow(_coreFlow.value.memoryArray.convertToMemoryVales())

    val registerArrayFlow: MutableStateFlow<List<RegisterValue>> =
        MutableStateFlow(_coreFlow.value.registerArray.convertToRegisters())

    private fun createCore() {
        val (instructions: List<Instruction>, parsedVariableData: List<ParsedData>) = Parser.parseDataFromFile(fileFlow.value)
        val program = Program(
            instructions = instructions,
            parsedData = parsedVariableData
        )
        _coreFlow.value = Core(program = program)
        updateFlows()
    }

    private fun updateFlows() {
        registerArrayFlow.value = _coreFlow.value.registerArray.convertToRegisters()
        memoryArrayFlow.value = _coreFlow.value.memoryArray.convertToMemoryVales()
    }

    val loadFile: (File) -> Unit = { newFile: File ->
        fileFlow.value = newFile
        createCore()
    }

    val executeProgram: () -> Unit = {
        _coreFlow.value.runProgram()
        updateFlows()
    }
}
