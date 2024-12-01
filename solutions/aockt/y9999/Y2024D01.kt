package aockt.y9999

import io.github.jadarma.aockt.core.Solution
import java.util.PriorityQueue
import kotlin.math.abs

/** A solution to a fictitious puzzle used for testing. */
object Y2024D01 : Solution {

    private fun parseInput(input: String): Pair<List<Int>, List<Int>> {
        val firstColumn = mutableListOf<Int>()
        val secondColumn = mutableListOf<Int>()

        input
            .splitToSequence('\n')
            .forEach { line ->
                val columns = line.split("\\s+".toRegex())
                if (columns.size >= 2) {
                    val first = columns[0].toIntOrNull()
                    val second = columns[1].toIntOrNull()

                    if (first != null && second != null) {
                        firstColumn.add(first)
                        secondColumn.add(second)
                    }
                }
            }

        return Pair(firstColumn, secondColumn)
    }

    override fun partOne(input: String) = parseInput(input).let { (list1, list2) ->
        val queue1 = PriorityQueue<Int>().apply { addAll(list1) }
        val queue2 = PriorityQueue<Int>().apply { addAll(list2) }
        var res = 0L
        while (queue1.isNotEmpty()) {
            res += abs(queue1.poll() - queue2.poll())
        }
        res
    }

    override fun partTwo(input: String) = parseInput(input).let { (list1, list2) ->
        val counts2 = list2.groupingBy { it }.eachCount()
        var res = 0L
        list1.forEach { num -> res += num * (counts2[num] ?: 0) }
        res
    }
}
