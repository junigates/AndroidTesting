package com.example.androidtesting.calculator

import com.nhaarman.mockitokotlin2.mock
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
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
    fun multiply2Numbers(){
        val calculator = mock<Calculator> {  }
        Mockito.`when`(calculator.multiply(4,5)).thenReturn(4)
        val result = calculator.multiply(4,5)
        Assert.assertEquals(result,4)
    }

    @Test
    fun subtract2Numbers(){

        val calculator = mock<Calculator>()
        val myCalculator = MyCalculator(calculator)

        Mockito.`when`(myCalculator.subtract(10,2)).thenReturn(7)
        val result = myCalculator.subtract(10,2)

        Assert.assertEquals(result,8)
    }

    @Test
    fun add2NumbersWithMockito(){

        val calculator = mock<Calculator>()
        `when`(calculator.add(5,3)).thenReturn(9)

        val result = calculator.add(5,3)
        assertEquals(8,result)

    }

}