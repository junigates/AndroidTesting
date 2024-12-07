package com.example.androidtesting.utils

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.BeforeParam


@RunWith(value = Parameterized::class)
class ParameterizedHelperTest(val input: String, val expected_result: Boolean) {


    @Test
    fun isPallindrom(){
        assertEquals(Helper().isPallindrome(input), expected_result)
    }

    companion object{

        @BeforeParam
        fun beforeParam(){
            println("beforeParam")
        }


        @JvmStatic
        @Parameterized.Parameters(name = "{0} is Pallindrom - {1}")
        fun data() : List<Array<Any>>{

            return listOf<Array<Any>>(

                arrayOf("",true),
                arrayOf("junigates",false),
                arrayOf("k",true),
                arrayOf("level",true)

            )

        }
    }


}