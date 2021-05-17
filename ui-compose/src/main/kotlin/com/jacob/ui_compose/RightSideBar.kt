package com.jacob.ui_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jacob.ui_compose.models.Register

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

@Composable
fun RightSideBar(modifier: Modifier) {
    Column(
        modifier = Modifier.fillMaxHeight()
            .background(color = Color.LightGray)
            .padding(16.dp)
            .then(modifier)
    ) {
        Title()
        RegisterList(modifier = Modifier.weight(0.75f, true))
        Divider(color = Color.Gray, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
        CpsrRegisters(modifier = Modifier.weight(0.25f, true))
    }
}

@Composable
fun Title() {
    Text(
        "Registers:",
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        modifier = Modifier.padding(top = 8.dp, bottom = 8.dp)
    )
}

@Composable
fun RegisterList(modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(registers.size) {
            RegisterItem(registers[it])
        }
    }
}

@Composable
fun RegisterItem(register: Register) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(8.dp).fillMaxWidth()
    ) {
        Text(register.name)
        Text(register.value.toString())
    }
}

@Composable
fun CpsrRegisters(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            "CPSR Registers:",
            fontWeight = FontWeight.W500,
            fontSize = 24.sp,
        )
        LazyColumn {
            item(1) { RegisterItem(Register("Zero", 0)) }
            item(2) { RegisterItem(Register("Negative", 0)) }
            item(3) { RegisterItem(Register("Carry", 0)) }
            item(4) { RegisterItem(Register("Overflow", 0)) }
        }
    }
}
