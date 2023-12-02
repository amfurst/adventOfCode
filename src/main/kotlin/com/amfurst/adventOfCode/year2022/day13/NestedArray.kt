package com.amfurst.adventOfCode.year2022.day13

class NestedItemList<T> {
    private val array: ArrayList<NestedItemList<T>>?
    private val singleItem: T?

    constructor(array: ArrayList<NestedItemList<T>>) {
        this.array = array
        singleItem = null
    }

    constructor(singleItem: T?) {
        this.singleItem = singleItem
        array = null
    }
}

class NestedArray<T> {
    private val array: ArrayList<NestedArrayItem<T>> = arrayListOf()

    fun add(value: T?) {
        array.add(NestedArrayItem(value))
    }

    fun addNested(nestedArray: NestedArray<T>) {
        array.add(NestedArrayItem(nestedArray.array))
    }

    fun flatten(): ArrayList<T?> = array.asSequence()
        .flatMap { it.asSequence() }
        .toCollection(arrayListOf())

    override fun toString() = array.joinToString(prefix = "[", postfix = "]")
}