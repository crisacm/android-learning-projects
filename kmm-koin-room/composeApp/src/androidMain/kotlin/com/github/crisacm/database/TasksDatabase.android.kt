package com.github.crisacm.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<TasksDatabase> {
  val appContext = ctx.applicationContext
  val dbFile = appContext.getDatabasePath(DATABASE_NAME)
  return Room.databaseBuilder<TasksDatabase>(
    context = ctx,
    name = dbFile.absolutePath
  )
}
