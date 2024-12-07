package com.example.androidtesting.calculator

import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class CalculatorTest{



    @Test
    fun add2Numbers(){

        val calculator = Calculator()

        val result = calculator.add(5,3)
        assertEquals(8,result)

    }

    @Test
    fun add2NumbersWithMockito(){

        val calculator = mock<Calculator>()
        `when`(calculator.add(5,3)).thenReturn(9)

        val result = calculator.add(5,3)
        assertEquals(8,result)

    }

}