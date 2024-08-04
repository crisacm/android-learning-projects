package com.github.crisacm.animefinder.navigation.destination

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.github.crisacm.animefinder.navigation.InfoDestination
import com.github.crisacm.animefinder.presentation.screens.list.ListContracts
import com.github.crisacm.animefinder.presentation.screens.list.ListViewModel
import com.github.crisacm.animefinder.presentation.screens.list.composables.ListScreen

@Composable
fun ListScreenDestination(navController: NavController) {
  val viewModel: ListViewModel = hiltViewModel()
  ListScreen(
    state = viewModel.viewState.value,
    effectFlow = viewModel.effect,
    onEventSent = viewModel::setEvent,
    onNavigationRequested = {
      when (it) {
        is ListContracts.Effect.Navigation.ToDetails -> {
          navController.navigate(InfoDestination(animeId = it.id))
        }
      }
    }
  )
}
