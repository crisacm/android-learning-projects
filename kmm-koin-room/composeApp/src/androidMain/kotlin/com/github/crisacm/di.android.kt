package com.github.crisacm

import androidx.room.RoomDatabase
import com.github.crisacm.database.TasksDatabase
import com.github.crisacm.database.getDatabaseBuilder
import org.koin.dsl.module

actual fun platformModule() = module {
  single<RoomDatabase.Builder<TasksDatabase>> {
    getDatabaseBuilder(get())
  }
}