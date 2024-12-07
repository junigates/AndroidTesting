package com.example.androidtesting.roomtesting

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "TodoTable")
data class TodoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val isCompleted: Boolean
)