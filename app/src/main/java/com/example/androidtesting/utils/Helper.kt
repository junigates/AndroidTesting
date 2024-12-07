package com.example.androidtesting.utils

class Helper {


    fun isPallindrome(text: String) : Boolean{

        return if(text.isNotEmpty()) text.first().equals(text.last(),true)
        else true

    }

}