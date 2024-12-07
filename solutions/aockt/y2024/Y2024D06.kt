package aockt.y2024

import io.github.jadarma.aockt.core.Solution

/** A solution to a fictitious puzzle used for testing. */
object Y2024D06 : Solution {

    private val dirs = listOf(
        -1 to 0,
        0 to 1,
        1 to 0,
        0 to -1
    )
    private var start = 0 to 0
    private var traceLength = 0

    private fun parseInput(input: String): List<CharArray> {
        val lines = mutableListOf<CharArray>()
        input
            .splitToSequence('\n')
            .forEach { lines.add(it.toCharArray()) }

        var startFound = false
        for (r in lines.indices) {
            for (c in lines[0].indices) {
                if (lines[r][c] == '^') {
                    start = r to c
                    startFound = true
                    break
                }
            }
            if (startFound) break
        }
        return lines
    }

    override fun partOne(input: String): Int = parseInput(input).let { map ->
        return getStepsWithCoordinates(map).first.also { traceLength = it }
    }

    override fun partTwo(input: String): Int = parseInput(input).let { map ->
        val trace = getStepsWithCoordinates(map.map { it.copyOf() }).second
        var res = 0
        trace.forEach { obstacle ->
            if (isLooping(map.map { it.copyOf() }, obstacle)) {
                res++
            }
        }
        return res
    }

    private fun getStepsWithCoordinates(map: List<CharArray>): Pair<Int, Set<Pair<Int, Int>>> {
        var count = 1
        val trace = mutableSetOf<Pair<Int, Int>>()
        var cur = start
        var dir = 0
        while (cur.first in map.indices && cur.second in map[0].indices) {
            val next = (cur.first + dirs[dir].first) to (cur.second + dirs[dir].second)
            if (next.first !in map.indices || next.second !in map[0].indices) break
            when (map[next.first][next.second]) {
                '#' -> dir = (dir + 1) % 4
                '.', 'X','^' -> {
                    cur = next
                    if (map[next.first][next.second] == '.') count++
                    map[next.first][next.second] = 'X'
                    trace.add(next.first to next.second)
                }
            }
        }
        return count to trace
    }


    private fun isLooping(map: List<CharArray>, obstacle: Pair<Int, Int>): Boolean {
        var dir = 0
        var cur = start
        val visited = mutableSetOf<Triple<Int, Int, Int>>()
        map[obstacle.first][obstacle.second] = 'O'

        while (cur.first in map.indices && cur.second in map[0].indices) {
            val next = cur.first + dirs[dir].first to cur.second + dirs[dir].second
            if (next.first !in map.indices || next.second !in map[0].indices) break
            when (map[next.first][next.second]) {
                '#', 'O' -> dir = (dir + 1) % 4
                '.', 'X', '^' -> {
                    val state = Triple(next.first, next.second, dir)
                    if (state in visited) return true
                    visited.add(state)
                    cur = next
                }
            }
        }
        return false
    }
}