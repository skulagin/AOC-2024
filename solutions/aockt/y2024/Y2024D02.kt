package aockt.y2024

import io.github.jadarma.aockt.core.Solution

/** A solution to a fictitious puzzle used for testing. */
object Y2024D02 : Solution {

    private fun parseInput(input: String): List<List<Int>> {
        val lines = mutableListOf<List<Int>>()

        input
            .splitToSequence('\n')
            .forEach { line ->
                lines.add(line.split(" ").mapNotNull { it.toIntOrNull() })
            }

        return lines
    }

    override fun partOne(input: String): Int = parseInput(input).let { lines ->
        lines.count { it.isSafe() }
    }

    override fun partTwo(input: String) = parseInput(input).let { lines ->
        lines.count { line ->
            line.indices.any {
                line.toMutableList().apply { removeAt(it) }.isSafe()
            }
        }
    }

    private fun List<Int>.isSafe(): Boolean {
        if (last() == first()) return false
        val isIncreasing = last() > first()
        for (i in 1..lastIndex) {
            if ((isIncreasing && get(i) - get(i - 1) !in 1..3) ||
                (isIncreasing.not() && get(i - 1) - get(i) !in 1..3)
            ) return false
        }
        return true
    }
}
