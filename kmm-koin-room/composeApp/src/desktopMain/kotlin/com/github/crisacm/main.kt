package com.github.crisacm

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMM-Koin-Room",
    ) {
        initKoin()
        App()
    }
}