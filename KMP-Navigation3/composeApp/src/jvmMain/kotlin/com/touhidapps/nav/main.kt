package com.touhidapps.nav

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "KMP-Navigation3-Demo",
    ) {
        App()
    }
}