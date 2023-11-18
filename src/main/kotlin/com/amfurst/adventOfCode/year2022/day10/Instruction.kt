package com.amfurst.adventOfCode.year2022.day10

enum class Command {
    noop,
    addx
}

class Instruction(val cmd: Command, val value: Int) {
    companion object Factory {
        fun create(input: String): Instruction {
            val cmd: Command = Command.valueOf(input.split(" ")[0])
            val value: Int = if (cmd == Command.addx) input.split(" ")[1].toInt() else 0
            return Instruction(cmd, value)
        }
    }
}