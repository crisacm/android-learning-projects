package com.github.crisacm.domain.usecase

import com.github.crisacm.domain.model.Task
import com.github.crisacm.domain.repository.TaskRepository

class DeleteTaskUseCase(
  private val taskRepository: TaskRepository
) {
  suspend operator fun invoke(task: Task) {
    taskRepository.deleteTask(task)
  }
}