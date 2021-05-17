package com.jacob.ui_compose.right_sidebar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.jacob.ui_compose.cpsrRegister

@Composable
fun CpsrRegisters(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            "CPSR Registers:",
            fontWeight = FontWeight.W500,
            fontSize = 24.sp,
        )
        LazyColumn {
            items(items = cpsrRegister) {
                RegisterItem(it)
            }
        }
    }
}
