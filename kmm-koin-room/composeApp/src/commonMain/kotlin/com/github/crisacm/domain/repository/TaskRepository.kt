package com.github.crisacm.domain.repository

import com.github.crisacm.domain.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
  fun getAllTasks(): Flow<List<Task>>
  suspend fun addTask(task: Task)
  suspend fun updateTask(task: Task)
  suspend fun deleteTask(task: Task)
}