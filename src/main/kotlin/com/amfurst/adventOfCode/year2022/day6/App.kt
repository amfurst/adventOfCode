package com.amfurst.adventOfCode.year2022.day6

fun main() {
    val input = readInput("/2022/day6/input.txt")
    val result = input?.let { processInput(it) }
    println(result)
}

fun readInput(fileName: String): String? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readText()
}

fun processInput(input: String): Int {
    var runningTotal = 14
    val lastFour: MutableList<Char> = input.substring(0, runningTotal).toMutableList()
    var uniqueChars: Set<Char> = lastFour.toSet()

    while (uniqueChars.size < 14) {
        lastFour.removeAt(0)
        lastFour.add(input[runningTotal++])
        uniqueChars = lastFour.toSet()
    }

    return runningTotal
}