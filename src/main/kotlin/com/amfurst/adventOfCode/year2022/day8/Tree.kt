package com.amfurst.adventOfCode.year2022.day8

class Tree(val height: Int) {
    val vis: Visibility = Visibility()
}

class Visibility {
    var upCount: Int = 0
    var downCount: Int = 0
    var leftCount: Int = 0
    var rightCount: Int = 0

    val visibilityScore: Int
        get() = upCount * downCount * leftCount * rightCount

}