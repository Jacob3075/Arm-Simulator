package com.jacob.ui_compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import com.jacob.ui_compose.models.CodeViewerLine
import com.jacob.ui_compose.models.MemoryValue
import com.jacob.ui_compose.models.Register
import java.io.File


val registers = listOf(
    Register("REGISTER 1:", 0),
    Register("REGISTER 2:", 0),
    Register("REGISTER 3:", 0),
    Register("REGISTER 4:", 0),
    Register("REGISTER 5:", 0),
    Register("REGISTER 6:", 0),
    Register("REGISTER 7:", 0),
    Register("REGISTER 8:", 0),
    Register("REGISTER 9:", 0),
    Register("REGISTER 10:", 0),
    Register("REGISTER 11:", 0),
    Register("REGISTER 12:", 0),
    Register("REGISTER 13:", 0),
    Register("REGISTER 14:", 0),
    Register("REGISTER 15:", 0),
)

val cpsrRegister = listOf(
    Register("Overflow", 0),
    Register("Carry", 0),
    Register("Negative", 0),
    Register("Zero", 0),
)

val memoryArray = List(75) { MemoryValue(it, 0) }

fun File.convertToCodeLines(): List<CodeViewerLine> {
    return try {
        this.readLines()
            .mapIndexed { index, line -> CodeViewerLine(index + 1, line) }
    } catch (e: Exception) {
        println(e)
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
