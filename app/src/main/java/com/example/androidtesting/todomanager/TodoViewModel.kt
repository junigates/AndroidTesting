package com.example.androidtesting.todomanager

import android.content.Context
import androidx.arch.core.executor.TaskExecutor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson

class TodoViewModel : ViewModel() {

//    Features
//Add Task: The user can add a task with a title and a description.
//View All Tasks: Display a list of all tasks.
//Mark Task as Completed: Allow the user to mark a task as completed.
//Delete Task: Allow the user to delete a specific task from the list.


//    private lateinit var taskList : MutableList<SingleTask>
    private val _taskList: MutableLiveData<MutableList<SingleTask>> = MutableLiveData(ArrayList())
    val taskList: LiveData<MutableList<SingleTask>>
        get() = _taskList


    fun allTasksFromFile(context: Context, fileName: String) : MutableList<SingleTask>?{

        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        _taskList.postValue(Gson().fromJson(json,Array<SingleTask>::class.java).toMutableList())

        return _taskList.value

    }

    fun addANewTask(singleTask: SingleTask) : MutableList<SingleTask>?{
        _taskList.value?.add(singleTask)
        _taskList.postValue(taskList.value)
        return _taskList.value
    }

    fun makeATaskAsCompleted(id: Int, check: Boolean): MutableList<SingleTask>? {
        _taskList.postValue(_taskList.value?.map { task ->
            if (task.id == id) task.copy(isCompleted = check) else task
        }?.toMutableList())
        return _taskList.value
    }

    fun deleteSingleTask(id: Int) : MutableList<SingleTask>?{
        _taskList.value?.removeIf { it.id == id }
        _taskList.postValue(_taskList.value?.filterNot { it.id == id }?.toMutableList())
        return _taskList.value
    }


}