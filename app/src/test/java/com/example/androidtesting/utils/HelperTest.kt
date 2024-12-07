package com.example.androidtesting.utils

import android.content.Context
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.BeforeClass

import org.junit.Test
import org.junit.runners.Parameterized.BeforeParam

class HelperTest {

    lateinit var helper : Helper


    @Before
    fun setup(){
        helper = Helper()
        println("Before")
    }

    @After
    fun tearDown(){
        println("After")
    }

    @Test
    fun should_Not_A_Pallindrome() {
        val test = helper.isPallindrome("junigates")
        println("should_Not_A_Pallindrome")
        assertEquals(false,test)
    }


    @Test
    fun empty_String_Is_A_Pallindrome() {
        val test = helper.isPallindrome("")
        println("empty_String_Is_A_Pallindrome")
        assertEquals(true,test)
    }


    @Test
    fun is_A_Pallindrome() {
        val test = helper.isPallindrome("level")
        println("is_A_Pallindrome")
        assertEquals(true,test)
    }

    companion object {
        @JvmStatic
        @BeforeClass
        fun setUpBeforeCalss(): Unit {
            println("setUpBeforeCalss")
        }
    }

}