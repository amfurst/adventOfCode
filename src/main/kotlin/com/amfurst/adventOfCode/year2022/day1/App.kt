package com.amfurst.adventOfCode.year2022.day1

fun main() {
    val inputLines = object {}.javaClass.getResourceAsStream("/2022/day1/input.txt")?.bufferedReader()?.readLines()

    val elves = inputLines?.fold(mutableListOf(Elf())) { elves, item ->
        if (item.isEmpty()) {
            elves.add(Elf())
        } else {
            elves.last() add item.toInt()
        }
        elves
    }

    println(elves?.maxOfOrNull { it.getTotal() })
}

class Elf {
    private val items: MutableList<Int> = mutableListOf()

    infix fun add(item: Int) = items.add(item)

    fun getTotal() = items.reduce {acc, next -> acc + next}
}