package com.github.crisacm.animefinder.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.github.crisacm.animefinder.navigation.destination.InfoScreenDestination
import com.github.crisacm.animefinder.navigation.destination.ListScreenDestination

const val TRANSITION_DURATION = 500

@Composable
fun Navigation() {
  val navController = rememberNavController()

  NavHost(
    navController = navController,
    startDestination = ListDestination,
    enterTransition = {
      fadeIn(
        animationSpec = tween(TRANSITION_DURATION)
      ) + slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, tween(TRANSITION_DURATION))
    },
    exitTransition = {
      fadeOut(
        animationSpec = tween(TRANSITION_DURATION)
      ) + slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, tween(TRANSITION_DURATION))
    }
  ) {
    composable<ListDestination> {
      ListScreenDestination(navController = navController)
    }

    composable<InfoDestination> {
      val args = it.toRoute<InfoDestination>()
      InfoScreenDestination(navController = navController, animeId = args.animeId)
    }
  }
}
