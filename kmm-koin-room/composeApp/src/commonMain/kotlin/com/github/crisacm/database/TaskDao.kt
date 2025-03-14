package com.github.crisacm.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
  @Query("SELECT * FROM TaskEntity")
  suspend fun getAll(): List<TaskEntity>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  suspend fun insert(task: TaskEntity)

  @Update(onConflict = OnConflictStrategy.IGNORE)
  suspend fun update(task: TaskEntity)

  @Delete
  suspend fun delete(task: TaskEntity)
}