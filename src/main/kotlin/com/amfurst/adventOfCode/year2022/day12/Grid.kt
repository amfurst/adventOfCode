package com.amfurst.adventOfCode.year2022.day12

class Grid(input: List<String>) {
    private var grid: List<List<Point>> =
        input.mapIndexed { rIdx, row -> row.mapIndexed { cIdx, col -> Point(rIdx, cIdx, col) } }

    fun getStartingPoints(): List<Point> {
        return grid.flatten().filter { it.height == 'a' || it.height == 'S' }.map { it.height = 'a'; it }
    }

    fun getEnd(): Point {
        return grid.flatten().filter { it.height == 'E' }.map { it.height = 'z'; it }.first()
    }

    private fun inGrid(point: IntArray): Boolean {
        return point[0] >= 0 && point[0] < grid.size && point[1] >= 0 && point[1] < grid[0].size
    }

    private fun getNeighbors(current: Point): List<Point> {
        val row = current.row
        val col = current.col

        return listOf(
            intArrayOf(row - 1, col),
            intArrayOf(row + 1, col),
            intArrayOf(row, col - 1),
            intArrayOf(row, col + 1)
        ).filter { inGrid(it) }.map { grid[it[0]][it[1]] }
    }

    private fun reset() {
        grid.forEach { row ->
            row.forEach {
                it.dist = Int.MAX_VALUE
                it.visited = false
            }
        }
    }

    fun findPath(start: Point, end: Point): Int {
        reset()
        start.dist = 0
        var current: Set<Point> = setOf(start)

        while (!current.contains(end) && current.isNotEmpty()) {
            current = current.filterTo(HashSet()) { !it.visited }
            current = current.flatMapTo(HashSet()) { p ->
                p.visited = true
                getNeighbors(p).filter { it.height.code <= p.height.code + 1 }
                    .map { it.dist = minOf(p.dist + 1, it.dist); it }
            }
        }

        return end.dist
    }
}