package com.study.kotlinstudy.kotlin_in_action.interfaceWithClass

open class RichButton : Clickable {
    fun disable() {}
    open fun animate() {}
    final override fun click() {}
}