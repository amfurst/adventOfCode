package com.amfurst.adventOfCode.year2022.day7

abstract class Command {
    companion object Factory {
        fun build(input: String): Command {
            val args: List<String> = input.split(" ")

            return when (args[0]) {
                "$" -> if (args[1] == "cd") CmdCd(args[2]) else CmdLs()
                "dir" -> Listing(args[1], true)
                else -> Listing(args[1], false, args[0].toInt())
            }
        }
    }
}

class CmdCd(val dir: String): Command() {}

class CmdLs(): Command() {}

class Listing(name: String, isDir: Boolean, size: Int = 0): Command() {
    val file: File = if (isDir) Directory(name) else File(name, size)
}