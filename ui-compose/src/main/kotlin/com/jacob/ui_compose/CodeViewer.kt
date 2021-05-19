package com.jacob.ui_compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jacob.ui_compose.models.CodeViewerLine
import java.io.File

@Composable
fun CodeViewer(modifier: Modifier, file: File) {
    val codeLines = file.convertToCodeLines()
    val state = rememberLazyListState()

    Box(modifier = modifier.border(width = 1.dp, color = Color.DarkGray)) {
        LazyColumn(modifier = Modifier.padding(8.dp).fillMaxSize(), state = state) {
            items(items = codeLines) {
                Line(it)
            }
        }
        Scrollbar(
            modifier = Modifier.align(Alignment.CenterEnd),
            state = state,
            itemCount = codeLines.size,
            averageItemSize = 27.2.dp
        )
    }
}

@Composable
private fun Line(codeViewerLine: CodeViewerLine) {
    Row(
        modifier = Modifier.fillMaxSize().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LineNumber(
            lineNumber = codeViewerLine.lineNumber,
            modifier = Modifier.weight(0.1f, false).padding(top = 1.dp)
        )
        LineContent(
            lineContent = codeViewerLine.lineContent,
            modifier = Modifier.weight(1f, true)
        )
    }
}

@Composable
private fun LineContent(lineContent: String, modifier: Modifier) {
    Text(
        text = lineContent,
        style = TextStyle(
            color = Color.DarkGray,
            fontSize = 16.sp
        ),
        modifier = modifier
    )
}

@Composable
private fun LineNumber(lineNumber: Int, modifier: Modifier) {
    Text(
        text = "%02d".format(lineNumber),
        style = TextStyle(
            color = Color.LightGray,
            fontSize = 12.sp
        ),
        modifier = modifier
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun Scrollbar(modifier: Modifier, state: LazyListState, itemCount: Int, averageItemSize: Dp) {
    VerticalScrollbar(
        modifier = modifier,
        adapter = rememberScrollbarAdapter(
            scrollState = state,
            itemCount = itemCount,
            averageItemSize = averageItemSize
        )
    )
}
