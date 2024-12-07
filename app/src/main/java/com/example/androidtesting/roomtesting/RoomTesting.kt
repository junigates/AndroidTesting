package com.example.androidtesting.roomtesting

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.room.Room
import com.example.androidtesting.R

class RoomTesting : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_room_testing)


        val database = Room.databaseBuilder(this,TodoDb::class.java,"junaid-to-db")
            .allowMainThreadQueries()
            .build()

    }
}