package com.study.kotlinstudy.kotlin_in_action

import strings.lastChar
import java.lang.StringBuilder
import kotlin.collections.Collection

class Collection


fun <T> joinToString(
        collection: Collection<T>,
        separator: String,
        prefix: String,
        postfix: String,
):String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if(index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}


fun main(args: Array<String>) {
    val set = hashSetOf(1, 7, 10)
    val list = arrayListOf(1, 6, 23)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")
    val strings = listOf("first", "second", "fourteenth", "four", "nineteenth", "five")
    val numbers = setOf(1, 24, 300, 59, 34)
    val setNumbers = listOf(1, 2, 3)

    println("Kotlin".lastChar())
    /*
    println(strings.last()) // Collection의 마지막 원소
    println(numbers.maxOrNull())    // Collection의 최댓값
    println("setNumbers: $setNumbers, min = ${setNumbers.minOrNull()} max = ${setNumbers.maxOrNull()}") // setNumbers: [1, 2, 3], min = 1 max = 3
    println(joinToString(strings,";","(",")"))

    println(set.javaClass)  // javaClass는 자바의 getClass()와 동일하다.
    println(list.javaClass)
    println(map.javaClass)*/

}
