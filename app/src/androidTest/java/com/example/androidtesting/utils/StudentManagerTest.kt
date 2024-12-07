package com.example.androidtesting.utils

import android.app.Activity
import android.content.Context
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.google.gson.JsonSyntaxException
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.io.FileNotFoundException

class StudentManagerTest {

    val fileName = "StudentList.json"
    val errorFileName = "ErrorException.json"

    lateinit var studentManager : StudentManager
    lateinit var context: Context

    @Before
    fun setUp() {

        context = ApplicationProvider.getApplicationContext<Context>()
        studentManager = StudentManager()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun initStudentFileAndGetList() {

        studentManager.initStudentFile(context,fileName)

        val array = studentManager.studentList
        assertEquals(true,array.isNotEmpty())
    }


    @Test
    fun getASingleStudent() {
        studentManager.initStudentFile(context,fileName)
        val student = studentManager.getSingleStudent(3)
        assertEquals(true,student!=null)
    }

    @Test
    fun studentNotFound() {
        studentManager.initStudentFile(context,fileName)
        val student = studentManager.getSingleStudent(6)
        assertEquals(true,student==null)
    }


    @Test(expected = JsonSyntaxException::class)
    fun jsonSyntaxExceptionShouldBe() {
        studentManager.initStudentFile(context,errorFileName)
    }

    @Test(expected = FileNotFoundException::class)
    fun studentFileNotFound() {

        studentManager.initStudentFile(context,"STB")

    }

}