package com.jacob.ui_compose.state

import arrow.core.Either
import com.jacob.core_lib.common.Errors
import com.jacob.core_lib.core.Core
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
    private val coreFlow: MutableStateFlow<Core> = MutableStateFlow(emptyCore())

    val memoryArrayFlow: MutableStateFlow<List<MemoryValue>> =
        MutableStateFlow(coreFlow.value.memoryArray.convertToMemoryVales())

    val registerArrayFlow: MutableStateFlow<List<RegisterValue>> =
        MutableStateFlow(coreFlow.value.registerArray.convertToRegisters())

    val errorsFlow: MutableStateFlow<List<Errors>> = MutableStateFlow(emptyList())

    private fun createCore() = when (val parseProgramFromFile = Parser.parseProgramFromFile(fileFlow.value)) {
        is Either.Right -> {
            val program = parseProgramFromFile.value
            coreFlow.value = Core(program = program)
            errorsFlow.value = emptyList()
            updateFlows()
        }
        is Either.Left -> {
            val nonEmptyListErrors = parseProgramFromFile.value
            errorsFlow.value = nonEmptyListErrors
            coreFlow.value = emptyCore()
        }
    }

    private fun updateFlows() {
        registerArrayFlow.value = coreFlow.value.registerArray.convertToRegisters()
        memoryArrayFlow.value = coreFlow.value.memoryArray.convertToMemoryVales()
    }

    val loadFile: (File) -> Unit = { newFile: File ->
        fileFlow.value = newFile
        createCore()
    }

    val executeNextInstruction: () -> Unit = {
        coreFlow.value.runNextInstruction()
        updateFlows()
    }

    val resetProgram: () -> Unit = {
        coreFlow.value.resetProgram()
        updateFlows()
    }

    val executeProgram: () -> Unit = {
        coreFlow.value.runProgram()
        updateFlows()
    }
}
