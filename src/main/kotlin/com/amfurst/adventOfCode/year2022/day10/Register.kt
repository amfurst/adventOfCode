package com.amfurst.adventOfCode.year2022.day10

class Register {
    var screen: MutableList<MutableList<Char>> = mutableListOf()
    private var spriteIdx: Int = 1
    private var position: Int = 0

    fun executeInstruction(instr: Instruction) {
        executeCycle(instr, false)

        if (instr.cmd == Command.addx) {
            executeCycle(instr, true)
        }
    }

    private fun executeCycle(instr: Instruction, midCycle: Boolean) {
        if ((position % 40) in listOf(spriteIdx - 1, spriteIdx, spriteIdx + 1)) {
            addPixel(position, '#')
        } else {
            addPixel(position, '.')
        }

        if (midCycle) {
            spriteIdx += instr.value
        }

        position++
    }

    private fun addPixel(position: Int, pixel: Char) {
        if (position % 40 == 0) {
            screen.add(mutableListOf())
        }

        screen.last().add(pixel)
    }
}