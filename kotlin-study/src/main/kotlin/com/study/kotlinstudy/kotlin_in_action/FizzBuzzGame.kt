package com.study.kotlinstudy.kotlin_in_action

import java.util.*

class FizzBuzzGame {
}

fun fizzBuzz(i: Int) = when {
    i % 15 == 0 -> "FizzBuzz "
    i % 5 == 0 -> "FizzBuzz "
    i % 3 == 0 -> "FizzBuzz "
    else -> "$i "
}




fun main(args:Array<String>) {
/*    // 1부터 100까지
    for (i in 1..100) {
//        print(fizzBuzz(i))
    }

    // 100부터 1까지 짝수만
    for (i in 100 downTo 1 step 2) {
        print(fizzBuzz(i))
    }*/

    val binaryReps = TreeMap<Char, String>()

    for (c in 'A'..'F') {
        val binary = Integer.toBinaryString(c.code)
        binaryReps[c] = binary
    }

    for ((letter, binary) in binaryReps) {
        println("$letter = $binary")
    }
}
