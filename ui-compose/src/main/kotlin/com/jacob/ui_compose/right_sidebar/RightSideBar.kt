package com.jacob.ui_compose.right_sidebar

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RightSideBar(modifier: Modifier) {
    Column(modifier = modifier) {
        Title()
        RegisterList(modifier = Modifier.weight(0.75f, true))
        Divider(color = Color.Gray, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
        CpsrRegisters(modifier = Modifier.weight(0.25f, true))
    }
}

@Composable
private fun Title() {
    Text(
        "Registers:",
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    )
}
