package com.github.crisacm.animefinder.presentation.screens.info

import com.github.crisacm.animefinder.data.api.model.Anime
import com.github.crisacm.animefinder.presentation.base.ViewEvent
import com.github.crisacm.animefinder.presentation.base.ViewSideEffect
import com.github.crisacm.animefinder.presentation.base.ViewState

class InfoContracts {

  sealed interface Event: ViewEvent {
    data object onBack : Event
  }

  data class State(
    val isLoading: Boolean = false,
    val error: String? = null,
    val data: Anime? = null
  ) : ViewState

  sealed interface Effect : ViewSideEffect {
    sealed interface Navigation : Effect {
      data object onBack : Navigation
    }
  }
}
