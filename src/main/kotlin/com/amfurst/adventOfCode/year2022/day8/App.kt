package com.amfurst.adventOfCode.year2022.day8

fun main() {
    val inputLines = readInput("/2022/day8/input.txt")
    val result = inputLines?.let { processInput(it) }
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>): Int {
    val trees: List<List<Tree>> = input.map { heights -> heights.toList().map { Tree(it.digitToInt()) } }
    var upDownHeightMap: List<MutableMap<Int, Int>> = List(trees[0].size) { mutableMapOf() }

    trees.forEachIndexed { i, tree -> processRow(tree, i, upDownHeightMap, false) }

    upDownHeightMap = List(trees[0].size) { mutableMapOf() }

    trees.asReversed().forEachIndexed{ i, tree -> processRow(tree, i, upDownHeightMap, true) }

    return trees.maxOf { getMaxScore(it) }
}

fun getMaxScore(row: List<Tree>): Int {
    return row.maxOf { it.vis.visibilityScore }
}

fun processRow(row: List<Tree>, rowIdx: Int, upDownHeightMap: List<MutableMap<Int, Int>>, isReverse: Boolean) {
    val leftHeightMap: MutableMap<Int, Int> = mutableMapOf()
    val rightHeightMap: MutableMap<Int, Int> = mutableMapOf()

    if (isReverse) {
        row.forEachIndexed { i, tree ->
            tree.vis.downCount = rowIdx - (upDownHeightMap[i].filter { it.key >= tree.height }.values.maxOrNull() ?: 0)
            upDownHeightMap[i][tree.height] = rowIdx
        }
    } else {
        row.forEachIndexed { i, tree ->
            tree.vis.leftCount = i - (leftHeightMap.filter { it.key >= tree.height }.values.maxOrNull() ?: 0)
            leftHeightMap[tree.height] = i

            tree.vis.upCount = rowIdx - (upDownHeightMap[i].filter { it.key >= tree.height }.values.maxOrNull() ?: 0)
            upDownHeightMap[i][tree.height] = rowIdx
        }

        row.asReversed().forEachIndexed { i, tree ->
            tree.vis.rightCount = i - (rightHeightMap.filter { it.key >= tree.height }.values.maxOrNull() ?: 0)
            rightHeightMap[tree.height] = i
        }
    }
}