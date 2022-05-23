package com.study.kotlinstudy.kotlin_in_action.interfaceWithClass

class button : Clickable {
    override fun click() = println("I was clicked")
    override fun showOff() {
        super<Clickable>.showOff()
    }
}

fun main(args: Array<String>) {
    button().click()    //  I was clicked
    button().showOff()

}

