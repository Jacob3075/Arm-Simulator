package com.jacob.ui_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun CodeViewer(weight: Modifier) {
    Box(modifier = weight) {
        Surface(color = Color.DarkGray, modifier = Modifier.fillMaxSize()) {}
    }
}
