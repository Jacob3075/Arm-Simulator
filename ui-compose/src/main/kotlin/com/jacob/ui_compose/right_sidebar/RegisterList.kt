package com.jacob.ui_compose.right_sidebar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.jacob.ui_compose.models.Register
import com.jacob.ui_compose.registers

@Composable
fun RegisterList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(items = registers) {
            RegisterItem(it)
        }
    }
}

@Composable
fun RegisterItem(register: Register, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(8.dp).fillMaxWidth()
    ) {
        Text(register.name)
        Text(register.value.toString())
    }
}
