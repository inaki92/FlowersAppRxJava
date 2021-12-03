package com.inaki.flowersapp

import com.inaki.flowersapp.model.FlowersItem

fun String.myExtension(): String {
    return "MyExtension"
}

fun MutableList<Int>.myOtherExtendedFunc(index1: Int, index2: Int) {
    val tmp = this[index1] // 'this' corresponds to the list
    this[index1] = this[index2]
    this[index2] = tmp
}

fun FlowersItem.getNew(flowers: FlowersItem): FlowersItem {
    return flowers
}

fun timeToMillis(time: Long): Long {
    return time / 1000
}