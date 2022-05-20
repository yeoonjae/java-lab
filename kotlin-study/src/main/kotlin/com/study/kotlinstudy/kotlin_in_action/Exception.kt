package com.study.kotlinstudy.kotlin_in_action

import java.io.BufferedReader
import java.io.StringReader
import java.lang.IllegalArgumentException

class Exception {
    fun exceptionTest(percentage: Int) {
        if (percentage !in 0..100) {
            throw IllegalArgumentException("A percentage value must be between 0 and 100: $percentage")
        }
    }

    fun readNumber(reader: BufferedReader): Int? {
        try {
            val line = reader.readLine()
            return Integer.parseInt(line)
        } catch (e: NumberFormatException) {
            return null
        }finally {
            reader.close()
        }
    }

}


fun readNumber2(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine()) // 이 식이 try 식의 값이 된다.
    } catch (e: NumberFormatException) {
        return
    }
    println(number)
}


fun main(args:Array<String>) {
    val reader = BufferedReader(StringReader("123"))
    readNumber2(reader)
}
