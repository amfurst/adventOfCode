package com.amfurst.adventOfCode.year2022.day11

class Item(worry: Long) {
    var worry: Long = worry
        private set

    fun inspect(op: (Long, Long) -> Long, modifier: String, modulo: Long) {
        val iMod: Long = if (modifier == "old") worry else modifier.toLong()
        worry = op(worry, iMod) % modulo
    }
}