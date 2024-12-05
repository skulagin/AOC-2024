package aockt.y2024

import io.github.jadarma.aockt.core.Solution

/** A solution to a fictitious puzzle used for testing. */
object Y2024D05 : Solution {

    private fun parseInput(input: String): Input {
        val rules = mutableMapOf<Int, MutableSet<Int>>()
        val requests = mutableListOf<List<Int>>()

        val (first, second) = input.split("\n\n")
        first
            .splitToSequence('\n')
            .forEach {
                val nums = it.split('|')
                val x = nums[0].toInt()
                val y = nums[1].toInt()
                rules[x] = (rules[x] ?: mutableSetOf()).apply { add(y) }
            }

        second
            .splitToSequence('\n')
            .forEach {
                val nums = it.split(',')
                requests.add(nums.map { it.toInt() })
            }

        return Input(rules, requests)
    }

    override fun partOne(input: String): Int = parseInput(input).let { (rules, requests) ->
        var res = 0
        for (request in requests) {
            if (request.isCorrect(rules)) res += request[request.size / 2]
        }
        return res
    }

    override fun partTwo(input: String): Int = parseInput(input).let { (rules, requests) ->
        var res = 0
        for (request in requests) {
            if (!request.isCorrect(rules)) {
                res += request.sortByRules(rules)[request.size / 2]
            }
        }
        return res
    }

    private fun List<Int>.sortByRules(rules: Map<Int, Set<Int>>): List<Int> {
        val result = this.toMutableList()
        for (i in lastIndex downTo 1) {
            for (j in i - 1 downTo 0) {
                rules[result[i]]?.let { set ->
                    if (result[j] in set) {
                        val temp = result[i]
                        result[i] = result[j]
                        result[j] = temp
                    }
                }
            }
        }
        return result
    }

    private fun List<Int>.isCorrect(rules: Map<Int, Set<Int>>): Boolean {
        for (i in lastIndex downTo 1) {
            for (j in i - 1 downTo 0) {
                rules[get(i)]?.let { if (get(j) in it) return false }
            }
        }
        return true
    }

    data class Input(
        val rules: Map<Int, Set<Int>>,
        val requests: List<List<Int>>
    )
}