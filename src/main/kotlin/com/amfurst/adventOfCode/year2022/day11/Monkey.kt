package com.amfurst.adventOfCode.year2022.day11

class Monkey(
    private val tribe: Tribe,
    val id: Int,
) {
    lateinit var items: MutableList<Item>
    lateinit var worryOp: (Long, Long) -> Long
    lateinit var worryModifier: String
    var testVal: Long = 0
    var trueId: Int = 0
    var falseId: Int = 0
    var inspections: Long = 0

    fun takeTurn() {
        items.forEach { inspect(it) }
        items = mutableListOf()
    }

    fun inspect(item: Item) {
        inspections++
        item.inspect(worryOp, worryModifier, tribe.getModulo())
        evaluate(item)
    }

    fun evaluate(item: Item) {
        if ((item.worry % testVal) == 0L) {
            tribe.getMonkey(trueId)?.receive(item)
        } else {
            tribe.getMonkey(falseId)?.receive(item)
        }
    }

    fun receive(item: Item) {
        items.add(item)
    }
}