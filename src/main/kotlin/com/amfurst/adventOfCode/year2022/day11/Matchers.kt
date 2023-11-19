package com.amfurst.adventOfCode.year2022.day11

class Matcher {
    companion object Factory {
        val ID: Regex = "Monkey (?<id>\\d+):".toRegex()
        val ITEMS: Regex = "\\s+Starting items: (?<items>(\\d+(?:, )?)+)".toRegex()
        val OPERATION: Regex = "\\s+Operation: new = old (?<operator>.) (?<value>old|\\d+)".toRegex()
        val TEST: Regex = "\\s+Test: divisible by (?<divisor>\\d+)".toRegex()
        val TRUE: Regex = "\\s+If true: throw to monkey (?<id>\\d+)".toRegex()
        val FALSE: Regex = "\\s+If false: throw to monkey (?<id>\\d+)".toRegex()

        private fun getMatchingGroups(regex: Regex, str: String): MatchGroupCollection? {
            return regex.find(str)?.groups
        }

        fun getId(str: String): Int {
            return getMatchingGroups(ID, str)?.get("id")!!.value.toInt()
        }

        fun getItems(str: String): MutableList<Item> {
            val itemList = getMatchingGroups(ITEMS, str)?.get("items")!!.value
            return itemList.split(", ").map { Item(it.toLong()) }.toMutableList()
        }

        fun getOp(str: String): (Long, Long) -> Long {
            val opStr = getMatchingGroups(OPERATION, str)?.get("operator")!!.value
            return when (opStr) {
                "*" -> Long::times
                "+" -> Long::plus
                "-" -> Long::minus
                "/" -> Long::div
                else -> throw Exception("Unsupported Operation.")
            }
        }

        fun getOpModifier(str: String): String {
            return getMatchingGroups(OPERATION, str)?.get("value")!!.value
        }

        fun getTest(str: String): Long {
            return getMatchingGroups(TEST, str)?.get("divisor")!!.value.toLong()
        }

        fun getTrueId(str: String): Int {
            return getMatchingGroups(TRUE, str)?.get("id")!!.value.toInt()
        }

        fun getFalseId(str: String): Int {
            return getMatchingGroups(FALSE, str)?.get("id")!!.value.toInt()
        }
    }
}