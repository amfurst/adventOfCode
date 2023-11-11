package com.amfurst.adventOfCode.year2022.day5

fun main() {
    val inputLines = readInput("/2022/day5/input.txt")
    val result = inputLines?.let { processInput(it) }
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>): String {
    val splitIndex = input.indexOfFirst { it.isEmpty() }

    val dock: Dock = Dock(input.subList(0, splitIndex - 1).reversed())
    val moves: List<Move> = input.subList(splitIndex + 1, input.size).map { Move(it) }

    dock.executeMoves(moves)

    return dock.getTopCrates()
}

class Dock(input: List<String>) {
    private val contents: MutableList<MutableList<Char>> = mutableListOf()

    init {
        for (i in 1..<input[0].length step 4) {
            contents.add( input.map { it[i] }.filter { it.isLetter() }.toMutableList() )
        }
    }

    fun executeMoves(moves: List<Move>) {
        moves.forEach { executeMove(it) }
    }

    private fun executeMove(move: Move) {
        val source = contents[move.source - 1]
        val dest = contents[move.dest - 1]

        dest.addAll(source.drop(source.size - move.count))
        contents[move.source - 1] = source.dropLast(move.count).toMutableList()
    }

    fun getTopCrates(): String {
        return contents.map { it.last() }.toString()
    }
}

class Move(input: String) {
    val count:Int = input.split(" ")[1].toInt()
    val source:Int = input.split(" ")[3].toInt()
    val dest:Int = input.split(" ")[5].toInt()
}
