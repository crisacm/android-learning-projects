package com.github.crisacm.animefinder.navigation.destination

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.crisacm.animefinder.presentation.screens.info.InfoContracts
import com.github.crisacm.animefinder.presentation.screens.info.InfoViewModel
import com.github.crisacm.animefinder.presentation.screens.info.composables.InfoScreen

@Composable
fun InfoScreenDestination(navController: NavController, animeId: Long) {
  val viewModel = hiltViewModel(
    creationCallback = { factory: InfoViewModel.InfoViewModelFactory -> factory.create(animeId) }
  )

  InfoScreen(
    state = viewModel.viewState.value,
    effectFlow = viewModel.effect,
    onEventSent = viewModel::setEvent,
    onNavigationRequested = {
      when (it) {
        InfoContracts.Effect.Navigation.onBack -> navController.popBackStack()
      }
    }
  )
}
