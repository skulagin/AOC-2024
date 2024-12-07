package aockt.y2024

import io.github.jadarma.aockt.test.AdventDay
import io.github.jadarma.aockt.test.AdventSpec

@AdventDay(2024, 6, "Guard Gallivant")
class Y2024D06Test : AdventSpec<Y2024D06>({
    partOne {
        listOf(
            "....#.....\n" +
                    ".........#\n" +
                    "..........\n" +
                    "..#.......\n" +
                    ".......#..\n" +
                    "..........\n" +
                    ".#..^.....\n" +
                    "........#.\n" +
                    "#.........\n" +
                    "......#..."
        ) shouldAllOutput 41
    }

    partTwo {
        listOf(
            "....#.....\n" +
                    ".........#\n" +
                    "..........\n" +
                    "..#.......\n" +
                    ".......#..\n" +
                    "..........\n" +
                    ".#..^.....\n" +
                    "........#.\n" +
                    "#.........\n" +
                    "......#..."
        ) shouldAllOutput 6
    }
})
