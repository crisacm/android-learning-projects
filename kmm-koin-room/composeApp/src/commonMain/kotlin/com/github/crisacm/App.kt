@file:OptIn(KoinExperimentalAPI::class)

package com.github.crisacm

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.github.crisacm.ui.screens.home.HomeScreen
import com.github.crisacm.ui.screens.home.HomeViewModel
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
@Preview
fun App() {
  MaterialTheme {
    val viewModel = koinViewModel<HomeViewModel>()
    HomeScreen(
      state = viewModel.state.value,
      onEvent = viewModel::handleEvent,
      effect = viewModel.effect
    )
  }
}