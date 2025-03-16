package com.github.crisacm.domain.model

data class Task(
  var id: Long = 0L,
  val title: String,
  val createdAt: Long,
  var isCompleted: Boolean
)