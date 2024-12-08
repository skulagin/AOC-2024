package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import kotlin.math.abs

/** A solution to a fictitious puzzle used for testing. */
object Y2024D08 : Solution {

    private fun parseInput(input: String): List<CharArray> {
        val lines = mutableListOf<CharArray>()
        input
            .splitToSequence('\n')
            .forEach { lines.add(it.toCharArray()) }

        return lines
    }

    override fun partOne(input: String): Int = parseInput(input).let { lines ->
        val antiNodesMap = Array(lines.size) { CharArray(lines[0].size) { '.' } }
        val antennas = mutableMapOf<Char, MutableSet<Pair<Int, Int>>>()
        for (r in lines.indices) {
            for (c in lines[0].indices) {
                val cell = lines[r][c]
                if (cell != '.') {
                    antennas[cell] = (antennas[cell] ?: mutableSetOf()).apply { add(r to c) }
                }
            }
        }
        antennas.values.map { it.toList() }.forEach { type ->
            for (i in 0 until type.lastIndex) {
                for (j in i + 1..type.lastIndex) {
                    val (r1, c1) = type[i]
                    val (r2, c2) = type[j]
                    val antiNode1 = (r1 + abs(r2 - r1) * if (r2 > r1) -1 else 1) to (c1 + abs(c2 - c1) * if (c2 < c1) 1 else -1)
                    val antiNode2 = (r2 + abs(r2 - r1) * if (r2 > r1) 1 else -1) to (c2 + abs(c2 - c1) * if (c2 < c1) -1 else 1)
                    if (antiNode1.first in lines.indices && antiNode1.second in lines[0].indices) {
                        antiNodesMap[antiNode1.first][antiNode1.second] = '#' // Always mark the position
                    }
                    if (antiNode2.first in lines.indices && antiNode2.second in lines[0].indices) {
                        antiNodesMap[antiNode2.first][antiNode2.second] = '#' // Always mark the position
                    }
                }
            }
        }

        return antiNodesMap.sumOf { it.count { it == '#' } }
    }

    override fun partTwo(input: String): Int = parseInput(input).let { lines ->
        val antiNodesMap = Array(lines.size) { CharArray(lines[0].size) { '.' } }
        val antennas = mutableMapOf<Char, MutableSet<Pair<Int, Int>>>()
        for (r in lines.indices) {
            for (c in lines[0].indices) {
                val cell = lines[r][c]
                if (cell != '.') {
                    antennas[cell] = (antennas[cell] ?: mutableSetOf()).apply { add(r to c) }
                }
            }
        }
        antennas.values.map { it.toList() }.forEach { type ->
            for (i in 0 until type.lastIndex) {
                for (j in i + 1..type.lastIndex) {
                    val (r1, c1) = type[i]
                    val (r2, c2) = type[j]
                    var i = 0
                    while (true) {
                        val antiNode1 = (r1 + abs(r2 - r1) * if (r2 > r1) -i else i) to (c1 + abs(c2 - c1) * if (c2 < c1) i else -i)
                        if (antiNode1.first in lines.indices && antiNode1.second in lines[0].indices) {
                            antiNodesMap[antiNode1.first][antiNode1.second] = '#'
                            i++
                        } else break
                    }
                    i = 0
                    while (true) {
                        val antiNode2 = (r2 + abs(r2 - r1) * if (r2 > r1) i else -i) to (c2 + abs(c2 - c1) * if (c2 < c1) -i else i)
                        if (antiNode2.first in lines.indices && antiNode2.second in lines[0].indices) {
                            antiNodesMap[antiNode2.first][antiNode2.second] = '#'
                            i++
                        } else break
                    }
                }
            }
        }

        return antiNodesMap.sumOf { it.count { it == '#' } }
    }
}