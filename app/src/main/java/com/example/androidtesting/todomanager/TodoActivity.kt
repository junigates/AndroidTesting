package com.example.androidtesting.todomanager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtesting.R
import kotlin.random.Random

class TodoActivity : AppCompatActivity() {

    var taskList: MutableList<SingleTask> = ArrayList()

    private val viewModel by lazy {
        TodoViewModel()
    }


    lateinit var etTaskName: EditText
    lateinit var btnAddTask: Button
    lateinit var btnShareApp: Button
    lateinit var etTaskDelete: EditText
    lateinit var btnDeleteTask: Button
    lateinit var tvData: TextView
    lateinit var btnMarkItComplete: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo)


        etTaskName = findViewById<EditText>(R.id.etTaskName)
        etTaskDelete = findViewById<EditText>(R.id.etTaskDelete)
        btnAddTask = findViewById<Button>(R.id.btnAddTask)
        btnDeleteTask = findViewById<Button>(R.id.btnDeleteTask)
        btnShareApp = findViewById<Button>(R.id.btnShareApp)
        btnMarkItComplete = findViewById<Button>(R.id.btnMarkItComplete)
        tvData = findViewById<TextView>(R.id.tvData)

        viewModel.taskList.observe(this){

            taskList = it
            updateTextUI()
            Log.w("junigates", "taskList ${it}")
        }

        viewModel.allTasksFromFile(this,"SingleTaskMockData.json")

        btnAddTask.setOnClickListener {
            if(isValid()){
                addNewTask()
            }
        }

        btnMarkItComplete.setOnClickListener {
            markAsComplete()
        }

        btnDeleteTask.setOnClickListener {
            if(isValidForDelete()){
                deleteATask()
            }
        }

        btnShareApp.setOnClickListener {
            openIntentChooser()
        }

    }

    private fun openIntentChooser() {

        val intent = Intent(Intent.ACTION_SEND)

        val chooser = Intent.createChooser(intent,"Please select an app")

        startActivity(chooser)

    }

    fun writeSomethingInText(text: String){

        tvData.text = text

    }

    private fun deleteATask() {
        val deleteTask = etTaskDelete.text.toString()
        viewModel.deleteSingleTask(deleteTask.toInt())
        Log.w("junigates", "deleteATask")
    }

    private fun isValidForDelete(): Boolean {
        if(etTaskDelete.text.trim().isEmpty()){
            Toast.makeText(this,"please enter task id to delete",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun updateTextUI(){
        val buffer = StringBuffer()
        taskList.forEachIndexed { index, singleTask ->
            buffer.append("${singleTask.id}-${singleTask.title} - ${singleTask.isCompleted}\n")
        }
        tvData.text = buffer.toString()

    }

    private fun isValid():  Boolean{
        if(etTaskName.text.trim().isEmpty()){
            Toast.makeText(this,"please enter task name",Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun addNewTask(){

        val title = etTaskName.text.toString()
        viewModel.addANewTask(SingleTask(Random.nextInt(10, 999),title,"",false))
        Log.w("junigates", "addNewTask")

    }

    private fun markAsComplete(){
        val deleteTask = etTaskDelete.text.toString()
        viewModel.makeATaskAsCompleted(deleteTask.toInt(),true)
    }


}