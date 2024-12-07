package com.example.androidtesting.todomanager

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.activityScenarioRule
import com.google.gson.JsonSyntaxException
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters
import java.io.FileNotFoundException


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class TodoViewModelTest{

//    Features
//    Add Task: The user can add a task with a title and a description.
//    View All Tasks: Display a list of all tasks.
//    Mark Task as Completed: Allow the user to mark a task as completed.
//    Delete Task: Allow the user to delete a specific task from the list.


    private lateinit var viewModel: TodoViewModel
    private var taskList: MutableList<SingleTask>? = ArrayList()


    @Before
    fun initAllDependencies(){

        val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel = TodoViewModel()
        println("before initAllDependencies: ${viewModel.allTasksFromFile(context,"SingleTaskMockData.json")?.size}")
        taskList = viewModel.allTasksFromFile(context,"SingleTaskMockData.json")
    }

    @Test
    fun test1_getAllTasksList(){

        println("Get complete list ${taskList?.size}")
    }


    @Test
    fun test4_addNewTask(){

        println("List size BEFORE adding new single task ${taskList?.size}")

        taskList = viewModel.addANewTask(SingleTask(taskList?.size?:1,"Task-${taskList?.size}","I have to complete this task",false))

        println("List size AFTER adding new single task ${taskList?.size}")

    }

    @Test
    fun test5_markTaskAsCompleted(){

        val id = 3

        println("BEFORE Marking ${taskList?.find { it.id == id}} Completed")

        taskList = viewModel.makeATaskAsCompleted(id,true)

        println("AFTER Marking ${taskList?.find { it.id == id}} Completed")

    }

    @Test
    fun test6_deleteATask(){

        val id = 3

        println("Task deletion before ${taskList.toString()}")

        taskList = viewModel.deleteSingleTask(id)

        println("Task deletion before ${taskList.toString()}")

    }

    @Test
    fun test7_printAList(){
        println("Task list${taskList.toString()}")
    }


    @Test(expected = FileNotFoundException::class)
    fun test2_FileNotFoundException(){

        val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel.allTasksFromFile(context,"SingleTaskMockData.jsn")

    }

    @Test(expected = JsonSyntaxException::class)
    fun test3_JsonSyntaxException(){
        val context = ApplicationProvider.getApplicationContext<Context>()
        viewModel.allTasksFromFile(context,"ErrorException.json")

    }




}