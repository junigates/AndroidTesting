package com.example.androidtesting.calculator

class MyCalculator(val calculator: Calculator) {

    fun add(x: Int, y: Int): Int {
        return calculator.add(x, y)
    }

    fun subtract(x: Int, y: Int): Int {
        return calculator.subtract(x, y)
    }

    fun multiply(x: Int, y: Int): Int {
        return calculator.multiply(x, y)
    }

    fun divide(x: Int, y: Int): Int {
        return calculator.divide(x, y)
    }
}