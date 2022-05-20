package com.study.kotlinstudy.kotlin_in_action

class In {
}
fun isLetter(c: Char) = c in 'a'..'z' || c in 'A'..'Z'
fun isNotDigit(c:Char) = c !in '0'..'9'

// when에서 in 사용하기
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!"
    else -> "I don't know"
}

fun main(args:Array<String>) {
    println(isLetter('q'))
    println(isNotDigit('x'))
    println(recognize('8'))
}
