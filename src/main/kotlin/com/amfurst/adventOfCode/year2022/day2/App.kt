package com.amfurst.adventOfCode.year2022.day2

fun main() {
    val inputLines = readInput("/2022/day2/input.txt")
    val result = processInput(inputLines)
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>?): Int? {
    return input?.map {setupRound(it).getScore()}?.reduce {acc, next -> acc + next}
}

fun setupRound(input: String): Round {
    val inputs = input.split(" ")
    return Round(convertChoice(inputs[0]), convertOutcome(inputs[1]))
}

fun convertChoice(code: String): Choice {
    if (code == "A") return Choice.ROCK
    if (code == "B") return Choice.PAPER
    return Choice.SCISSORS
}

fun convertOutcome(code: String): Outcome {
    if (code == "X" ) return Outcome.LOSE
    if (code == "Y") return Outcome.DRAW
    return Outcome.WIN
}

enum class Choice (val score: Int) {
    ROCK(1),
    PAPER(2),
    SCISSORS(3);
}

enum class Outcome(val score: Int) {
    LOSE(0),
    DRAW(3),
    WIN(6);
}

class Round (private val opponent: Choice, private val outcome: Outcome) {
    private val rockOutcomes = mapOf(Outcome.DRAW to Choice.ROCK, Outcome.WIN to Choice.PAPER, Outcome.LOSE to Choice.SCISSORS)
    private val paperOutcomes = mapOf(Outcome.LOSE to Choice.ROCK, Outcome.DRAW to Choice.PAPER, Outcome.WIN to Choice.SCISSORS)
    private val scissorOutcomes = mapOf(Outcome.WIN to Choice.ROCK, Outcome.LOSE to Choice.PAPER, Outcome.DRAW to Choice.SCISSORS)

    private val outcomeMap = mapOf(Choice.ROCK to rockOutcomes, Choice.PAPER to paperOutcomes, Choice.SCISSORS to scissorOutcomes)

    fun getScore(): Int {
        return outcome.score + outcomeMap[opponent]!![outcome]!!.score
    }
}