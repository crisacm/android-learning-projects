@file:OptIn(ExperimentalForeignApi::class)

package com.github.crisacm.database

import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun getDatabaseBuilder(): RoomDatabase.Builder<TasksDatabase> {
  val dbFilePath = documentDirectory() + "/$DATABASE_NAME"
  return Room.databaseBuilder<TasksDatabase>(
    name = dbFilePath,
  )
}

private fun documentDirectory(): String {
  val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
    directory = NSDocumentDirectory,
    inDomain = NSUserDomainMask,
    appropriateForURL = null,
    create = false,
    error = null,
  )
  return requireNotNull(documentDirectory?.path)
}