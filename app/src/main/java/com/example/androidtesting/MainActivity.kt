package com.example.androidtesting

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidtesting.utils.Student
import com.example.androidtesting.utils.StudentManager
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    var currentStudent = 1
    lateinit var studentManager : StudentManager
    lateinit var studentList : Array<Student>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        studentManager = StudentManager()
        studentManager.initStudentFile(this,"StudentList.json")
        studentList = studentManager.studentList

        findViewById<Button>(R.id.btnShareApp).setOnClickListener {
            shareApp()
        }

        findViewById<Button>(R.id.btnNextStudent).setOnClickListener {
            getNextStudent()
        }

        findViewById<Button>(R.id.btnNextPrevious).setOnClickListener {
            previousStudent()
        }

    }

    private fun getNextStudent(){
        if(currentStudent < studentList.size){
            currentStudent++
        }
        findAndPrint()
    }

    private fun findAndPrint(){
        studentList.find { it.id == currentStudent }?.let { std->
            findViewById<TextView>(R.id.tvStudent).text = std.toString()
        }
    }

    private fun previousStudent(){

        if(currentStudent>1){
            currentStudent--
        }
        findAndPrint()
    }

    private fun shareApp(){

        val intent = Intent(Intent.ACTION_SEND).apply {
            action = Intent.ACTION_SEND
            type = "text/plain" // Specify MIME type
            putExtra(Intent.EXTRA_TEXT, "junaid") // Add the text to share
        }

        val chooser = Intent.createChooser(intent,"Please select")

        startActivity(chooser)

    }


}