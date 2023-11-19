package com.amfurst.adventOfCode.year2022.day11

fun main() {
    val inputLines = readInput("/2022/day11/input.txt")
    val result = inputLines?.let { processInput(it) }
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>): Long {
    val tribe = Tribe()

    input.forEachIndexed { idx, line ->
        when (idx % 7) {
            0 -> tribe.addMonkey(Monkey(tribe, Matcher.getId(line)))
            1 -> tribe.monkeys.last().items = Matcher.getItems(line)
            2 -> {
                tribe.monkeys.last().worryOp = Matcher.getOp(line)
                tribe.monkeys.last().worryModifier = Matcher.getOpModifier(line)
            }

            3 -> tribe.monkeys.last().testVal = Matcher.getTest(line)
            4 -> tribe.monkeys.last().trueId = Matcher.getTrueId(line)
            5 -> tribe.monkeys.last().falseId = Matcher.getFalseId(line)
        }
    }

    for (i in 1..10000) {
        tribe.monkeys.forEach { it.takeTurn() }
    }

    tribe.monkeys.sortByDescending { it.inspections }
    return tribe.monkeys.take(2).fold(1) { acc, monkey -> acc * monkey.inspections }
}