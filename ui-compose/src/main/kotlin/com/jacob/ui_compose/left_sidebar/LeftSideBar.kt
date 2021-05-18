package com.jacob.ui_compose.left_sidebar

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jacob.ui_compose.memoryArray

@Composable
fun LeftSideBar(modifier: Modifier) {
    Column(modifier = modifier) {
        ControlSection(modifier = Modifier.weight(0.25f, true))
        Divider(color = Color.Gray, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
        MemorySection(modifier = Modifier.weight(0.75f, true))
    }
}

@Composable
fun ControlSection(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(modifier = Modifier.width(150.dp), onClick = {}) {
            Text("Load new File")
        }
        Button(modifier = Modifier.width(150.dp), onClick = {}) {
            Text("Execute File")
        }
    }
}

@Composable
fun MemorySection(modifier: Modifier) {
    Title()
    MemoryArrayGrid(modifier = modifier)
}

@Composable
fun Title() {
    Text(
        "Memory:",
        textDecoration = TextDecoration.Underline,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MemoryArrayGrid(modifier: Modifier = Modifier) {
    LazyVerticalGrid(cells = GridCells.Fixed(2), modifier = modifier) {
        items(items = memoryArray) {
            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier
                    .padding(8.dp)
                    .border(width = 1.dp, color = Color.Black)
                    .padding(vertical = 8.dp)
            ) {
                Text("${it.address}:")
                Text("${it.value}")
            }
        }
    }
}
