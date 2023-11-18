package com.amfurst.adventOfCode.year2022.day9

enum class DIRECTION {
    D, U, L, R
}

class Move(val direction: DIRECTION, val spaces: Int)