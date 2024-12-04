package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.*

/** A solution to a fictitious puzzle used for testing. */
object Y2024D04 : Solution {

    private const val XMAS = "XMAS"
    private const val MAS = "MAS"
    private val directions = listOf(
        0 to 1,
        1 to 1,
        1 to 0,
        1 to -1,
        0 to -1,
        -1 to -1,
        -1 to 0,
        -1 to 1
    )

    private fun parseInput(input: String): List<String> {
        val res = mutableListOf<String>()
        input
            .splitToSequence('\n')
            .forEach(res::add)
        return res
    }

    override fun partOne(input: String): Int = parseInput(input).let { arr ->
        var res = 0
        for (r in arr.indices) {
            for (c in arr.first().indices) {
                directions.indices.forEach { dir ->
                    res += arr.checkDirection(r, c, dir)
                }
            }
        }
        return res
    }

    private fun List<String>.checkDirection(r: Int, c: Int, dir: Int): Int {
        XMAS.forEachIndexed { i, letter ->
            val nR = r + directions[dir].first * i
            val nC = c + directions[dir].second * i
            if (nR !in indices || nC !in first().indices || get(nR)[nC] != letter) return 0
        }
        return 1
    }

    override fun partTwo(input: String): Int = parseInput(input).let { arr ->
        var res = 0
        for (r in arr.indices) {
            for (c in arr.first().indices) {
                if (arr[r][c] == 'A') res += arr.checkA(r, c)
            }
        }
        return res
    }

    private fun List<String>.checkA(r: Int, c: Int): Int {
        var sR = r - 1
        var sC = c - 1
        val builder = StringBuilder()
        (0..2).forEach { i ->
            val nR = sR + directions[1].first * i
            val nC = sC + directions[1].second * i
            if (nR !in indices || nC !in first().indices) return 0
            builder.append(get(nR)[nC])
        }
        val s1 = builder.toString()
        if (s1 != MAS && s1.reversed() != MAS) return 0

        sR = r - 1
        sC = c + 1
        builder.clear()
        (0..2).forEach { i ->
            val nR = sR + directions[3].first * i
            val nC = sC + directions[3].second * i
            if (nR !in indices || nC !in first().indices) return 0
            builder.append(get(nR)[nC])
        }
        val s2 = builder.toString()
        if (s2 != MAS && s2.reversed() != MAS) return 0
        return 1
    }
}