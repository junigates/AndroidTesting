package com.example.androidtesting.roomtesting

import android.widget.ToggleButton
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runners.MethodSorters


@FixMethodOrder(value = MethodSorters.NAME_ASCENDING)
class TodoDaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var database: TodoDb
    lateinit var todoDao: TodoDao


    @Before
    fun test1_setUp(){

        //everytime it will create new instance as it called before every test function
        // i tried with !::database.isInitialized by making it global but it was creating new instance everytime
        //we tried with inMemoryDatabaseBuilder() which is why it just stores in device ram not on persistent storage


        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TodoDb::class.java
        ).allowMainThreadQueries().build()


        //if we only use databaseBuilder it will store the data in persistent storage of the device

//        database = Room.databaseBuilder(
//            ApplicationProvider.getApplicationContext(),
//            TodoDb::class.java,
//            "junaid-db"
//        ).allowMainThreadQueries().build()

        todoDao = database.todoDao()

    }

    @Test
    fun test2_addNewSingleTask_ExpectedListWithTask() = runBlocking{

        val todoEntity = TodoEntity(0,"Play Cricket",false)
        todoDao.addATask(todoEntity)

        println("Add 1st task")

        val list = todoDao.getAllTasks().getOrAwaitValue {  }

        Assert.assertEquals(1,list.size)
        Assert.assertEquals("Play Cricket",list[0].name)

    }

    @Test
    fun test3_getAllTasks(){

        val list = todoDao.getAllTasks().getOrAwaitValue()
        println("All Tasks ${list.size}")
        Assert.assertEquals(list.isNotEmpty(), list.isNotEmpty())
    }


    @Test
    fun test4_deleteTask_expectedResult_NoList()= runBlocking{

        val todoEntity = TodoEntity(0,"Play Cricket",false)
        todoDao.addATask(todoEntity)


        val list = todoDao.getAllTasks().getOrAwaitValue()

        println("Before Delete Tasks $list")

        todoDao.delete()


        val list1 = todoDao.getAllTasks().getOrAwaitValue {  }
        Assert.assertEquals(0,list1.size)

    }


    @After
    fun tearDown(){
        if (::database.isInitialized) {
            database.close()
        }
    }



}