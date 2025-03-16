package com.github.crisacm

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController(
  configure = {
    initKoin()
  }) { App() }