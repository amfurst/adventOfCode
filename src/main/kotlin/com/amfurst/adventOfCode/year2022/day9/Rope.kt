package com.amfurst.adventOfCode.year2022.day9

class Rope(size: Int) {
    private val knots: List<Knot> = List(size) { Knot(0, 0) }
    var tailVisits: MutableSet<String> = mutableSetOf(knots.last().toString())

    fun executeMove(move: Move) {
        for (i in 1..move.spaces) {
            knots.first().move(move.direction)

            for (j in 1..<knots.size) {
                knots[j].follow(knots[j - 1])
            }
            tailVisits.add(knots.last().toString())
        }
    }
}