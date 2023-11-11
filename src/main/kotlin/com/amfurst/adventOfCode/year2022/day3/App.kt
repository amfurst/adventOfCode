package com.amfurst.adventOfCode.year2022.day3

fun main() {
    val inputLines = readInput("/2022/day3/input.txt")
    val result = processInput(inputLines)
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>?): Int? {
   //return input?.map {Sack(it).wrongItem()}?.fold(0) {total, item -> total + item.priority}
    val groups = input?.fold(mutableListOf(Group())) { groups, line ->
        if (groups.last().sacks.size == 3) {
            groups.add(Group())
        }

        groups.last() add line
        groups
    }

    return groups?.fold(0) { total, group -> total + group.getBadge().priority}
}

class Item(sVal: Char?) {
    val priority = if (sVal == null) 0 else (if (sVal.code > 97) (sVal.code - 96) else (sVal.code - 38))
}

class Sack(contents: String) {
    val allItems = contents.toSet()
    private val compOne = contents.substring(0, (contents.length / 2)).toSet()
    private val compTwo = contents.substring((contents.length / 2)).toSet()

    fun wrongItem(): Item {
       return Item(compOne.intersect(compTwo).firstOrNull())
    }
}

class Group() {
    val sacks: MutableList<Sack> = mutableListOf()

    infix fun add(contents: String) = sacks.add(Sack(contents))

    fun getBadge(): Item {
        return Item(sacks.fold(sacks[0].allItems) { badgeSet, sack -> badgeSet.intersect(sack.allItems)}.firstOrNull())
    }
}
