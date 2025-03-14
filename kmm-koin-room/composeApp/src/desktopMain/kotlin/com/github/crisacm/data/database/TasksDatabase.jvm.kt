package com.github.crisacm.data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import com.github.crisacm.database.DATABASE_NAME
import com.github.crisacm.database.TasksDatabase
import java.io.File

fun getDatabaseBuilder(): RoomDatabase.Builder<TasksDatabase> {
  val dbFile = File(System.getProperty("java.io.tmpdir"), DATABASE_NAME)
  return Room.databaseBuilder<TasksDatabase>(
    name = dbFile.absolutePath,
  )
}