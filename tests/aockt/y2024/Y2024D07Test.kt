package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 7, "Bridge Repair")
class Y2024D07Test : AdventSpec<Y2024D07>({
    partOne {
        listOf(
            "190: 10 19\n" +
                    "3267: 81 40 27\n" +
                    "83: 17 5\n" +
                    "156: 15 6\n" +
                    "7290: 6 8 6 15\n" +
                    "161011: 16 10 13\n" +
                    "192: 17 8 14\n" +
                    "21037: 9 7 18 13\n" +
                    "292: 11 6 16 20"
        ) shouldAllOutput 3749
    }

    partTwo {
        listOf(
            "190: 10 19\n" +
                    "3267: 81 40 27\n" +
                    "83: 17 5\n" +
                    "156: 15 6\n" +
                    "7290: 6 8 6 15\n" +
                    "161011: 16 10 13\n" +
                    "192: 17 8 14\n" +
                    "21037: 9 7 18 13\n" +
                    "292: 11 6 16 20"
        ) shouldAllOutput 11387
    }
})
