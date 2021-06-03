package com.jacob.ui_compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.jacob.core_lib.common.W
import com.jacob.core_lib.core.Core
import com.jacob.core_lib.core.MemoryArray
import com.jacob.core_lib.core.Program
import com.jacob.core_lib.core.RegisterArray
import com.jacob.core_lib.word.Word
import com.jacob.ui_compose.models.CodeViewerLine
import com.jacob.ui_compose.models.CpsrRegister
import com.jacob.ui_compose.models.MemoryValue
import com.jacob.ui_compose.models.RegisterValue
import java.io.File


val registersArray = listOf(
    RegisterValue("REGISTER 1:", 0),
    RegisterValue("REGISTER 2:", 0),
    RegisterValue("REGISTER 3:", 0),
    RegisterValue("REGISTER 4:", 0),
    RegisterValue("REGISTER 5:", 0),
    RegisterValue("REGISTER 6:", 0),
    RegisterValue("REGISTER 7:", 0),
    RegisterValue("REGISTER 8:", 0),
    RegisterValue("REGISTER 9:", 0),
    RegisterValue("REGISTER 10:", 0),
    RegisterValue("REGISTER 11:", 0),
    RegisterValue("REGISTER 12:", 0),
    RegisterValue("REGISTER 13:", 0),
    RegisterValue("REGISTER 14:", 0),
    RegisterValue("REGISTER 15:", 0),
)

val cpsrRegister = listOf(
    RegisterValue("Overflow", 0),
    RegisterValue("Carry", 0),
    RegisterValue("Negative", 0),
    RegisterValue("Zero", 0),
)

val memoryArray = List(75) { MemoryValue(it, 0) }

fun File.convertToCodeLines(): List<CodeViewerLine> {
    return try {
        this.readLines()
            .mapIndexed { index, line -> CodeViewerLine(index + 1, line) }
    } catch (exception: Exception) {
        return listOf()
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Scrollbar(modifier: Modifier, state: LazyListState, itemCount: Int, averageItemSize: Dp) {
    VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(
            scrollState = state,
            itemCount = itemCount,
            averageItemSize = averageItemSize
        )
    )
}

fun MemoryArray.convertToMemoryVales(): List<MemoryValue> =
    if (this.mainMemory.isEmpty()) memoryArray
    else {
        val maxIndex = maxOf(75, mainMemory.size)
        (0..maxIndex).asSequence()
            .map { index ->
                val wordFromCore: Word = mainMemory.getOrNull(index) ?: 0.W
                MemoryValue(index, wordFromCore.value)
            }
            .toList()
    }

fun RegisterArray.convertToRegisters() =
    if (this.registers.isEmpty()) registersArray
    else registers.map {
        RegisterValue(it.key.name, it.value.getRegisterValue().value)
    }

@OptIn(ExperimentalStdlibApi::class)
fun RegisterValue.toCpsrRegister() = value.toString().let {
    val negative = it.getOrNull(0)?.digitToInt() ?: 0
    val zero = it.getOrNull(1)?.digitToInt() ?: 0
    val carry = it.getOrNull(2)?.digitToInt() ?: 0
    val overFlow = it.getOrNull(3)?.digitToInt() ?: 0
    return@let CpsrRegister(negative, zero, carry, overFlow)
}

fun emptyCore(): Core {
    val program = Program(emptyList(), emptyList())
    return Core(program = program)
}
