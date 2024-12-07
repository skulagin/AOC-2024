package aockt.y2024

import io.github.jadarma.aockt.core.Solution

/** A solution to a fictitious puzzle used for testing. */
object Y2024D07 : Solution {

    private fun parseInput(input: String): List<Pair<Long, List<Int>>> {
        val lines = mutableListOf<Pair<Long, List<Int>>>()
        input
            .splitToSequence('\n')
            .forEach { line ->
                val colonSplit = line.split(": ")
                lines.add(colonSplit[0].toLong() to colonSplit[1].split(' ').map { it.toInt() })
            }
        return lines
    }

    override fun partOne(input: String): Long = parseInput(input).let { lines ->
        val res = lines.sumOf { (target, nums) ->
            if (nums.isTargetAimable(target, listOf('*', '+'))) target else 0L
        }
        return res
    }

    override fun partTwo(input: String): Long = parseInput(input).let { lines ->
        val res = lines.sumOf { (target, nums) ->
            if (nums.isTargetAimable(target, listOf('*', '+', '|'))) target else 0L
        }
        Long.MAX_VALUE
        return res
    }

    private fun List<Int>.isTargetAimable(target: Long, ops: List<Char>): Boolean {

        fun evaluate(numbers: List<Int>, operators: List<Char>): Long {
            var result = numbers[0].toLong()
            for (i in operators.indices) {
                result = when (operators[i]) {
                    '+' -> result + numbers[i + 1]
                    '*' -> result * numbers[i + 1]
                    '|' -> (result.toString() + numbers[i + 1].toString()).toLong()
                    else -> return 0L
                }
            }
            return result
        }

        fun tryOperators(index: Int, operators: MutableList<Char>): Boolean {
            if (index == lastIndex) return evaluate(this, operators) == target

            for (op in ops) {
                operators.add(op)
                if (tryOperators(index + 1, operators)) return true
                operators.removeAt(operators.size - 1)
            }
            return false
        }

        return tryOperators(0, mutableListOf())
    }
}