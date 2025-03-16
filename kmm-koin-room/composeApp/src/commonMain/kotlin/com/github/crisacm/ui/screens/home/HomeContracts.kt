package com.github.crisacm.ui.screens.home

import com.github.crisacm.domain.model.Task

object HomeContracts {
  sealed interface Events {
    data object OnLoad : Events
    data class OnTaskAdded(val task: Task) : Events
    data class OnTaskUpdated(val task: Task) : Events
    data class OnTaskDeleted(val task: Task) : Events
  }

  data class State(
    val tasks: List<Task> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null,
  )

  sealed interface Effect {
    data class ShowMsg(val msg: String) : Effect
  }
}