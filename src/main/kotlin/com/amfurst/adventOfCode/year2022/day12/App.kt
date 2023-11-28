package com.amfurst.adventOfCode.year2022.day12

fun main() {
    val inputLines = readInput("/2022/day12/input.txt")
    val result = inputLines?.let { processInput(it) }
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>): Int {
    val grid = Grid(input)
    val end = grid.getEnd()

    return grid.getStartingPoints().minOfOrNull { grid.findPath(it, end) } ?: 0
}