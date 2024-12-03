package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 2, "Red-Nosed Reports")
class Y2024D02Test : AdventSpec<Y2024D02>({
    partOne {
        listOf("7 6 4 2 1") shouldAllOutput 1
        listOf("1 2 7 8 9") shouldAllOutput 0
        listOf("7 6 4 2 1\n1 2 7 8 9\n9 7 6 2 1\n1 3 2 4 5\n8 6 4 4 1\n1 3 6 7 9") shouldAllOutput 2
    }
    partTwo {
        listOf("8 6 4 4 1") shouldAllOutput 1
        listOf("1 2 7 8 9") shouldAllOutput 0
        listOf("7 6 4 2 1\n1 2 7 8 9\n9 7 6 2 1\n1 3 2 4 5\n8 6 4 4 1\n1 3 6 7 9") shouldAllOutput 4
    }
})