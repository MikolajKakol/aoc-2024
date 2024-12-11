package dayX

import Day
import DayTest

object DaySolved : Day {

    override suspend fun part1(input: List<String>) = input

    override suspend fun part2(input: List<String>) = input
}


fun main() {
    val tester = DayTest(DaySolved)
    tester.testAll(1, 1, 1, 1)
}
