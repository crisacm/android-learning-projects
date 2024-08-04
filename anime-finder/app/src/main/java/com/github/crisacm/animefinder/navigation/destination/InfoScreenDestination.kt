package com.github.crisacm.animefinder.navigation.destination

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.github.crisacm.animefinder.presentation.screens.info.composables.InfoScreen

@Composable
fun InfoScreenDestination(navController: NavController, animeId: Long) {
  InfoScreen(
    modifier = Modifier,
    cover = "",
    genders = emptyList(),
    title = "",
    extraInfo = emptyList(),
    description = "",
    rating = 0.0
  )
}
