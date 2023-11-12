package com.amfurst.adventOfCode.year2022.day7

class FileSystem {
    val root: Directory = Directory("/")
    private var currentDir: Directory = root

    fun execute(cmd: Command) {
        if (cmd is CmdCd) {
            currentDir = when (cmd.dir) {
                ".." -> currentDir.parent!!
                "/" -> root
                else -> currentDir.moveTo(cmd.dir)
            }
        } else if (cmd is Listing) {
            currentDir.add(cmd.file)
        }
    }
}

open class File(val name: String, open val size: Int) {
    open val isDir: Boolean = false
}

class Directory(name: String, private var _parent: Directory? = null) : File(name, 0) {
    override val isDir: Boolean = true
    private val contents: MutableMap<String, File> = mutableMapOf()

    override val size: Int
        get() = contents.values.sumOf { it.size }

    var parent: Directory?
        get() = _parent
        set(value) { _parent = value }

    fun getDirectories(): List<Directory> {
        return contents.values.filter { it.isDir }.map { it as Directory }
    }

    fun moveTo(dir: String): Directory {
        return contents.getOrPut(dir) { Directory(dir, this) } as Directory
    }

    fun add(file: File) {
        if (file.isDir) { (file as Directory).parent = this }
        contents.putIfAbsent(file.name, file)
    }
}