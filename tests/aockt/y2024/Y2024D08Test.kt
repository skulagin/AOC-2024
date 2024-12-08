package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 8, "Resonant Collinearity")
class Y2024D08Test : AdventSpec<Y2024D08>({
    partOne {
        listOf(
            "............\n" +
                    "........0...\n" +
                    ".....0......\n" +
                    ".......0....\n" +
                    "....0.......\n" +
                    "......A.....\n" +
                    "............\n" +
                    "............\n" +
                    "........A...\n" +
                    ".........A..\n" +
                    "............\n" +
                    "............"
        ) shouldAllOutput 14
    }
    partTwo {
        listOf(
            "............\n" +
                    "........0...\n" +
                    ".....0......\n" +
                    ".......0....\n" +
                    "....0.......\n" +
                    "......A.....\n" +
                    "............\n" +
                    "............\n" +
                    "........A...\n" +
                    ".........A..\n" +
                    "............\n" +
                    "............"
        ) shouldAllOutput 34
    }
})
