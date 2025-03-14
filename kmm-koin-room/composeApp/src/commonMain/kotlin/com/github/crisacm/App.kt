package com.github.crisacm

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.github.crisacm.ui.screens.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        HomeScreen()
    }
}