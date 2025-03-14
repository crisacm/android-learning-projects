package com.github.crisacm.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
  @PrimaryKey(autoGenerate = true) val id: Long = 0L,
  val title: String,
  val isCompleted: Boolean
)
