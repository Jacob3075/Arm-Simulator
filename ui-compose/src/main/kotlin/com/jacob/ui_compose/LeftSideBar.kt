package com.jacob.ui_compose

import androidx.compose.desktop.AppManager
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jacob.ui_compose.models.MemoryValue
import java.awt.FileDialog
import java.io.File
import java.io.FilenameFilter


@Composable
fun LeftSideBar(modifier: Modifier, onClick: (File) -> Unit, fileName: String) {
    Column(modifier = modifier) {
        ControlSection(modifier = Modifier.weight(0.25f, true), onClick, fileName = fileName)
        Divider(color = Color.Gray, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
        MemorySection(modifier = Modifier.weight(0.75f, true))
    }
}

@Composable
private fun ControlSection(modifier: Modifier, onClick: (File) -> Unit, fileName: String?) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = fileName ?: "No File Selected",
            style = TextStyle(fontSize = 24.sp),
            maxLines = 1,
            modifier = Modifier.padding(bottom = 12.dp)
        )
        Button(modifier = Modifier.width(150.dp), onClick = {
            val selectedFile = selectFile() ?: return@Button
            onClick(selectedFile)
        }) {
            Text("Load new File")
        }
        Button(modifier = Modifier.width(150.dp), onClick = {}) {
            Text("Execute File")
        }
    }
}

private fun selectFile(): File? {
    val current = AppManager.focusedWindow ?: return null
    val jFrame = current.window

    val fileDialog = FileDialog(jFrame)
    fileDialog.filenameFilter = FilenameFilter { _, fileName ->
        fileName.split(".").last() == "s"
    }
    fileDialog.isVisible = true

    return try {
        val name = fileDialog.file
        val dir = fileDialog.directory
        println(dir + name)
        if (name == null || dir == null) {
            throw Exception()
        }
        File(dir + name)
    } catch (e: Exception) {
        null
    }
}

@Composable
private fun MemorySection(modifier: Modifier) {
    Title()
    MemoryArray(modifier = modifier)
}

@Composable
private fun Title() {
    Text(
        "Memory:",
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    )
}

@Composable
private fun MemoryArray(modifier: Modifier = Modifier) {
    val state = rememberLazyListState()

    Box(modifier = modifier) {
        MemoryArrayGrid(state = state)
        Scrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).padding(vertical = 8.dp),
            state = state,
            itemCount = (memoryArray.size + 1) / 2,
            averageItemSize = 49.dp
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun MemoryArrayGrid(state: LazyListState) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(end = 10.dp),
        state = state
    ) {
        items(items = memoryArray) {
            RowItem(it)
        }
    }
}

@Composable
private fun RowItem(memoryValue: MemoryValue) {
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .padding(8.dp)
            .border(width = 1.dp, color = Color.Black)
            .padding(vertical = 8.dp)
    ) {
        Text("${"%03d".format(memoryValue.address)}:")
        Text("%02d".format(memoryValue.value))
    }
}
