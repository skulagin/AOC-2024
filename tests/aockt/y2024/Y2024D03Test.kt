package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 3, "Red-Nosed Reports")
class Y2024D03Test : AdventSpec<Y2024D03>({
    partOne {
        listOf("mul(44,46)") shouldAllOutput 2024
        listOf("mul(123,4)") shouldAllOutput 492
        listOf("mul(6,9!") shouldAllOutput 0
        listOf("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))") shouldAllOutput 161
    }


    partTwo {
        listOf("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))") shouldAllOutput 48
    }
})

