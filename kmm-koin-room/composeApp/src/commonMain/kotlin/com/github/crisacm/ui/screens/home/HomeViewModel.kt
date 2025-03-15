package com.github.crisacm.ui.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.crisacm.domain.model.Task
import com.github.crisacm.domain.usecase.AddTaskUseCase
import com.github.crisacm.domain.usecase.DeleteTaskUseCase
import com.github.crisacm.domain.usecase.GetTasksUseCase
import com.github.crisacm.domain.usecase.UpdateTaskUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class HomeViewModel(
  private val getTasksUseCase: GetTasksUseCase,
  private val addTaskUseCase: AddTaskUseCase,
  private val updateTaskUseCase: UpdateTaskUseCase,
  private val deleteTaskUseCase: DeleteTaskUseCase
) : ViewModel() {

  private val _state: MutableState<HomeContracts.State> = mutableStateOf(HomeContracts.State())
  val state: State<HomeContracts.State> = _state

  private val _effect: Channel<HomeContracts.Effect> = Channel()
  val effect = _effect.receiveAsFlow()

  private fun setEffect(builder: () -> HomeContracts.Effect) {
    val effectValue = builder()
    viewModelScope.launch { _effect.send(effectValue) }
  }

  fun handleEvent(event: HomeContracts.Events) {
    when (event) {
      is HomeContracts.Events.OnLoad -> getTasks()
      is HomeContracts.Events.OnTaskAdded -> addTask(event.task)
      is HomeContracts.Events.OnTaskUpdated -> updateTask(event.task)
      is HomeContracts.Events.OnTaskDeleted -> deleteTask(event.task)
    }
  }

  private fun getTasks() {
    viewModelScope.launch(Dispatchers.IO) {
      getTasksUseCase.invoke()
        .collectLatest {
          _state.value = _state.value.copy(tasks = it, isLoading = false)
        }
    }
  }

  private fun addTask(task: Task) {
    viewModelScope.launch(Dispatchers.IO) {
      addTaskUseCase.invoke(task)
      setEffect { HomeContracts.Effect.ShowMsg("Tasks added") }
    }
  }

  private fun updateTask(task: Task) {
    viewModelScope.launch(Dispatchers.IO) {
      updateTaskUseCase.invoke(task)
    }
  }

  private fun deleteTask(task: Task) {
    viewModelScope.launch(Dispatchers.IO) {
      deleteTaskUseCase.invoke(task)
    }
  }
}