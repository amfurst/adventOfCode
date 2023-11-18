package com.amfurst.adventOfCode.year2022.day9

fun main() {
    val inputLines = readInput("/2022/day9/input.txt")
    val result = inputLines?.let { processInput(it) }
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>): Int {
    val rope = Rope(10)
    input.forEach { rope.executeMove(createMove(it)) }
    return rope.tailVisits.size
}

fun createMove(input: String): Move {
    val params: List<String> = input.split(" ")
    return Move(DIRECTION.valueOf(params[0]), params[1].toInt())
}
