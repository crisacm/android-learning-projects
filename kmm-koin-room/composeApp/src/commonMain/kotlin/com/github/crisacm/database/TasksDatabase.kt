package com.github.crisacm.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

const val DATABASE_NAME = "tasks.db"

@Database(entities = [TaskEntity::class], version = 1, exportSchema = false)
@ConstructedBy(TasksDatabaseConstructor::class)
abstract class TasksDatabase : RoomDatabase() {
  abstract fun taskDao(): TaskDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object TasksDatabaseConstructor : RoomDatabaseConstructor<TasksDatabase> {
  override fun initialize(): TasksDatabase
}

fun getRoomDatabase(
  builder: RoomDatabase.Builder<TasksDatabase>
): TasksDatabase {
  return builder
    .fallbackToDestructiveMigrationOnDowngrade(true)
    .setDriver(BundledSQLiteDriver())
    .setQueryCoroutineContext(Dispatchers.IO)
    .build()
}
