package com.github.crisacm.data.repository

import com.github.crisacm.data.mapper.asDomain
import com.github.crisacm.data.mapper.asEntity
import com.github.crisacm.database.TaskDao
import com.github.crisacm.domain.model.Task
import com.github.crisacm.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TaskRepositoryImpl(
  private val taskDao: TaskDao,
) : TaskRepository {
  override fun getAllTasks(): Flow<List<Task>> =
    taskDao.getAll().map { list -> list.map { it.asDomain() } }

  override suspend fun addTask(task: Task) {
    taskDao.insert(task.asEntity())
  }

  override suspend fun updateTask(task: Task) {
    taskDao.update(task.asEntity())
  }

  override suspend fun deleteTask(task: Task) {
    taskDao.delete(task.asEntity())
  }
}