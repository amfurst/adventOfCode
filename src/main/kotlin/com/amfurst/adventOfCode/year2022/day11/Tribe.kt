package com.amfurst.adventOfCode.year2022.day11

class Tribe {
    var monkeys: MutableList<Monkey> = mutableListOf()

    fun addMonkey(monkey: Monkey) {
        monkeys.add(monkey)
    }

    fun getMonkey(id: Int): Monkey? {
        return monkeys.find { it.id == id }
    }

    fun getModulo(): Long {
        return monkeys.fold(1) { acc, monkey -> acc * monkey.testVal }
    }
}