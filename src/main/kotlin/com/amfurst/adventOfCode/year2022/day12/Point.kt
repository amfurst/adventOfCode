package com.amfurst.adventOfCode.year2022.day12

class Point(val row: Int, val col: Int, var height: Char, var dist: Int = Int.MAX_VALUE, var visited: Boolean = false) {

    override fun equals(other: Any?): Boolean {
        if (other == null) return false
        if (other !is Point) return false

        return this.row == other.row && this.col == other.col
    }

    override fun hashCode(): Int {
        var result = row
        result = 31 * result + col
        return result
    }
}