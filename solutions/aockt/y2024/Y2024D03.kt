package aockt.y2024

import io.github.jadarma.aockt.core.Solution
import java.util.*

/** A solution to a fictitious puzzle used for testing. */
object Y2024D03 : Solution {

    private fun parseInput(input: String): String = input

    const val MUL = "mul"
    const val DO = "do()"
    const val DONT = "don't()"

    override fun partOne(input: String): Long = parseInput(input).let { string ->
        var res = 0L
        val stack = Stack<Char>()
        var i = 0
        while (i < string.length) {
            val c = string[i]
            when {
                stack.size < MUL.length -> {
                    if (c == MUL[stack.size]) stack.push(c) else stack.clear()
                    i++
                }

                stack.size == MUL.length -> {
                    if (c == '(') {
                        val mult = parseMultiply(i, string)
                        res += mult.first
                        i = mult.second
                        stack.clear()
                    } else {
                        stack.clear()
                        i++
                    }
                }
            }
        }
        return res
    }

    override fun partTwo(input: String): Long {
        var result = 0L
        var enabled = true
        var i = 0

        while (i < input.length) {
            // Check for "don't()"
            if (input.startsWith(DONT, i)) {
                enabled = false
                i += DONT.length
                continue
            }

            // Check for "do()"
            if (input.startsWith(DO, i)) {
                enabled = true
                i += DO.length
                continue
            }

            // Check for valid "mul(X,Y)"
            if (input.startsWith(MUL, i)) {
                val (product, newIndex) = parseMultiply(i + MUL.length, input)
                if (enabled) {
                    result += product
                }
                i = newIndex
                continue
            }
            i++
        }

        return result
    }

    private fun parseMultiply(startIndex: Int, input: String): Pair<Long, Int> {
        var i = startIndex
        if (i >= input.length || input[i] != '(') return 0L to i
        i++

        val num1 = StringBuilder()
        val num2 = StringBuilder()
        var readingSecond = false

        while (i < input.length) {
            when (val c = input[i]) {
                in '0'..'9' -> {
                    if (readingSecond) num2.append(c) else num1.append(c)
                }
                ',' -> {
                    if (readingSecond) return 0L to i
                    readingSecond = true
                }
                ')' -> {
                    val first = num1.toString().toLongOrNull() ?: return 0L to i
                    val second = num2.toString().toLongOrNull() ?: return 0L to i
                    return first * second to i + 1
                }
                else -> return 0L to i
            }
            i++
        }

        return 0L to i
    }
}