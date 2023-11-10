package com.amfurst.adventOfCode.year2022.day1

fun main() {
    val inputLines = readInput("/2022/day1/input.txt")
    val result = processInput(inputLines)
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>?): Int? {
    val elves = input?.fold(mutableListOf(Elf())) { elves, item ->
        if (item.isEmpty()) {
            elves.add(Elf())
        } else {
            elves.last() add item.toInt()
        }
        elves
    }

    elves?.sortByDescending { it.getTotal() }

    return elves?.take(3)?.fold(0) {total, elf -> total + elf.getTotal()}
}

class Elf {
    private val items: MutableList<Int> = mutableListOf()

    infix fun add(item: Int) = items.add(item)

    fun getTotal() = items.reduce {acc, next -> acc + next}
}