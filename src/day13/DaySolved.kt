package day13

import Day
import DayTest
import util.retrieveLongNumbers

object DaySolved : Day {

    override suspend fun part1(input: List<String>) = input.chunked(4)
        .map {
            val (ax, ay) = it[0].retrieveLongNumbers()
            val (bx, by) = it[1].retrieveLongNumbers()
            val (px, py) = it[2].retrieveLongNumbers()
            // y = ax+b
            // 1-> (4, 3)
            // 2-> (8, 6)
            // y = 3/4x
            fun fa(offset: Long): Long {
                val (prizeX, prizeY) = (px + offset to py + offset)
                val det = ax * by - ay * bx;
                val a = (prizeX * by - prizeY * bx) / det;
                val b = (ax * prizeY - ay * prizeX) / det;
                return if (ax * a + bx * b == prizeX && ay * a + by * b == prizeY) {
                    a * 3 + b
                } else {
                    0
                }
            }
            fa(0L)
        }
        .sum()


    override suspend fun part2(input: List<String>) = input.chunked(4)
        .map {
            val (ax, ay) = it[0].retrieveLongNumbers()
            val (bx, by) = it[1].retrieveLongNumbers()
            val (px, py) = it[2].retrieveLongNumbers()
            // y = ax+b
            // 1-> (4, 3)
            // 2-> (8, 6)
            // y = 3/4x
            fun fa(offset: Long): Long {
                val (prizeX, prizeY) = (px + offset to py + offset)
                val det = ax * by - ay * bx;
                val a = (prizeX * by - prizeY * bx) / det;
                val b = (ax * prizeY - ay * prizeX) / det;
                return if (ax * a + bx * b == prizeX && ay * a + by * b == prizeY) {
                    a * 3 + b
                } else {
                    0
                }
            }
            fa(10000000000000L)
        }
        .sum()

}


fun main() {
    val tester = DayTest(DaySolved)
    tester.testAll(480L, 36838L, Unit, 1)
}
