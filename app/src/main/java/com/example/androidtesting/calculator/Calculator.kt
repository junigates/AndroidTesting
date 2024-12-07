package com.example.androidtesting.calculator

class Calculator {

    //junigates
    //ok

    fun add(x: Int, y: Int): Int {
        return x + y
    }

    fun subtract(x: Int, y: Int): Int {
        return x - y
    }

    fun multiply(x: Int, y: Int): Int {
        return x * y
    }

    fun divide(x: Int, y: Int): Int {
        if (y == 0) {
            throw IllegalArgumentException("Division by zero is not allowed")
        }
        return x / y
    }
}