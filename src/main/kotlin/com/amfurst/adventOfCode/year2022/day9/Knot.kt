package com.amfurst.adventOfCode.year2022.day9

import kotlin.math.abs

class Knot(private var x: Int, private var y: Int) {
    override fun toString(): String {
        return "$x,$y"
    }

    fun move(dir: DIRECTION) {
        when (dir) {
            DIRECTION.U -> y++
            DIRECTION.D -> y--
            DIRECTION.L -> x--
            DIRECTION.R -> x++
        }
    }

    fun follow(other: Knot) {
        if (this on other || this touching other) return

        if (this above other) {
            this.y--
        }

        if (this below other) {
            this.y++
        }

        if (this leftOf other) {
            this.x++
        }

        if (this rightOf other) {
            this.x--
        }
    }

    private infix fun on(other: Knot): Boolean {
        return this.x == other.x && this.y == other.y
    }

    private infix fun touching(other: Knot): Boolean {
        val xDiff = abs(this.x - other.x)
        val yDiff = abs(this.y - other.y)

        return !(this on other) && ((xDiff == 0 && yDiff == 1) || (xDiff == 1 && yDiff == 0) || (xDiff == 1 && yDiff == 1))
    }

    private infix fun above(other: Knot): Boolean {
        return this.y > other.y
    }

    private infix fun below(other: Knot): Boolean {
        return this.y < other.y
    }

    private infix fun leftOf(other: Knot): Boolean {
        return this.x < other.x
    }

    private infix fun rightOf(other: Knot): Boolean {
        return this.x > other.x
    }
}