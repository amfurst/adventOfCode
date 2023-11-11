package com.amfurst.adventOfCode.year2022.day4

fun main() {
    val inputLines = readInput("/2022/day4/input.txt")
    val result = processInput(inputLines)
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>?): Int? {
    return input?.map { Pair(it) }?.count { it.anyOverlap() }
}

class Assignment(sVal: String) {
    private val start = sVal.split("-")[0].toInt()
    private val end = sVal.split("-")[1].toInt()

    fun contains(other: Assignment): Boolean {
        return this.start <= other.start && this.end >= other.start
    }

    fun containsAll(other: Assignment): Boolean {
        return this.start <= other.start && this.end >= other.end
    }
}

class Pair(sVal: String) {
    private val elfOne = Assignment(sVal.split(",")[0])
    private val elfTwo = Assignment(sVal.split(",")[1])

    fun anyOverlap(): Boolean {
        return elfOne.contains(elfTwo) || elfTwo.contains(elfOne)
    }

    fun fullOverlap(): Boolean {
        return elfOne.containsAll(elfTwo) || elfTwo.containsAll(elfOne)
    }
}
