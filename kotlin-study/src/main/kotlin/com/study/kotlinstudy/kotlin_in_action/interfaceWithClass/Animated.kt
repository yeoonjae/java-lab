package com.study.kotlinstudy.kotlin_in_action.interfaceWithClass

abstract class Animated {
    abstract fun animate()
    open fun stopAnimating() {  // 비추상 함수는 기본적으로 final이지만 open으로 오버라이드를 허용

    }
    fun animateTwice() {

    }
}