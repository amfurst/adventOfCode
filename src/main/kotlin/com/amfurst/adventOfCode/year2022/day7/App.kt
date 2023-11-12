package com.amfurst.adventOfCode.year2022.day7

fun main() {
    val inputLines = readInput("/2022/day7/input.txt")
    val result = inputLines?.let { processInput(it) }
    println(result)
}

fun readInput(fileName: String): List<String>? {
    return object {}.javaClass.getResourceAsStream(fileName)?.bufferedReader()?.readLines()
}

fun processInput(input: List<String>): Int {
    val sys = FileSystem()
    input.map { Command.build(it) }.forEach { sys.execute(it) }

    val dirs: List<Directory> = getDirectories(sys.root)

    val neededSpace = 30000000 - (70000000 - sys.root.size)

    return dirs.filter { it.size >= neededSpace }.minByOrNull { it.size }!!.size
}

fun getDirectories(dir: Directory): List<Directory> {
    val currentDirs: List<Directory> = dir.getDirectories()
    val allDirs: MutableList<Directory> = mutableListOf()

    if (currentDirs.isNotEmpty()) {
        for (element in currentDirs) {
            allDirs.addAll(getDirectories(element))
        }
    }

    allDirs.addAll(currentDirs)

    return allDirs
}