package com.github.crisacm.data.mapper

import com.github.crisacm.database.TaskEntity
import com.github.crisacm.domain.model.Task

fun Task.asEntity(): TaskEntity {
  return TaskEntity(
    id = id,
    title = title,
    isCompleted = isCompleted
  )
}

fun TaskEntity.asDomain(): Task {
  return Task(
    id = id,
    title = title,
    isCompleted = isCompleted
  )
}
