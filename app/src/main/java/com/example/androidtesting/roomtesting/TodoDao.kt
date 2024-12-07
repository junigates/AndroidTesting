package com.example.androidtesting.roomtesting

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TodoDao {

    @Insert
    suspend fun addATask(item: TodoEntity)

    @Update
    suspend fun updateTask(item: TodoEntity)

    @Delete
    suspend fun deleteSingleTask(item: TodoEntity)

    @Query("delete from TodoTable")
    suspend fun delete()

    @Query("select * from TodoTable")
    fun getAllTasks() : LiveData<List<TodoEntity>>

    @Query("select * from TodoTable where id = :taskId")
    suspend fun getSingleTask(taskId: Int) : TodoEntity


}