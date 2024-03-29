package com.jacob.ui_compose

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jacob.ui_compose.models.CpsrRegister
import com.jacob.ui_compose.models.RegisterValue

@Composable
fun RightSideBar(modifier: Modifier, registersArray: List<RegisterValue>) {
    Column(modifier = modifier) {
        Title()
        RegisterList(modifier = Modifier.weight(0.75f, true), registersArray)
        Divider(color = Color.Gray, modifier = Modifier.padding(top = 16.dp, bottom = 16.dp))
        CpsrRegisters(modifier = Modifier.weight(0.25f, true), registersArray.last().toCpsrRegister())
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

@Composable
private fun RegisterList(modifier: Modifier = Modifier, registersArray: List<RegisterValue>) {
    val state = rememberLazyListState()
    Box(modifier = modifier) {
        LazyColumn(state = state) {
            items(items = registersArray) {
                RegisterItem(it)
            }
        }
        Scrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().then(modifier),
            state = state,
            itemCount = registersArray.size,
            averageItemSize = 35.dp
        )
    }
}

@Composable
private fun RegisterItem(register: RegisterValue, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.padding(8.dp).fillMaxWidth()
    ) {
        Text(register.name)
        Text(register.value.toString())
    }
}

@Composable
private fun CpsrRegisters(modifier: Modifier = Modifier, cpsrRegister: CpsrRegister) {
    val cpsrRegisterArray = listOf(
        RegisterValue("Negative: ", cpsrRegister.negative),
        RegisterValue("Zero: ", cpsrRegister.zero),
        RegisterValue("Carry: ", cpsrRegister.carry),
        RegisterValue("OverFlow: ", cpsrRegister.overFlow),
    )
    Column(modifier = modifier) {
        Text(
            "CPSR Registers:",
            fontWeight = FontWeight.W500,
            fontSize = 24.sp,
        )
        LazyColumn {
            items(items = cpsrRegisterArray) {
                RegisterItem(it)
            }
        }
    }
}
