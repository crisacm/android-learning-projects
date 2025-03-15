package com.github.crisacm.domain.usecase

import com.github.crisacm.domain.model.Task
import com.github.crisacm.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow

class GetTasksUseCase(
  private val taskRepository: TaskRepository
) {
  operator fun invoke(): Flow<List<Task>> = taskRepository.getAllTasks()
}