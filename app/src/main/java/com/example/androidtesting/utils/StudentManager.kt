package com.example.androidtesting.utils

import android.content.Context
import com.google.gson.Gson

class StudentManager {


    var studentList : Array<Student> = arrayOf()

    fun initStudentFile(context: Context,fileName: String){

        val inputStream = context.assets.open(fileName)

        val size = inputStream.available()
        val buffer = ByteArray(size)

        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)

        studentList = Gson().fromJson(json, Array<Student>::class.java)
    }

    fun populateStudentsList(list: Array<Student>){
        studentList = list
    }

    fun getSingleStudent(id: Int) : Student? = studentList.find { it.id  == id }

}