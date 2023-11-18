package com.amfurst.adventOfCode.year2022.day10

fun main() {
    val inputLines = readInput("/2022/day10/input.txt")
    val result = inputLines?.let { processInput(it) }

    result?.forEach { println(String(it.toCharArray())) }
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>): List<List<Char>> {
    val register = Register()
    input.forEach { register.executeInstruction(Instruction.create(it)) }

    return register.screen
}