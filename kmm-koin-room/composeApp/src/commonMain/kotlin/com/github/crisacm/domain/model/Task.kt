package com.github.crisacm.domain.model

data class Task(
  var id: Long = 0L,
  val title: String,
  var isCompleted: Boolean
)