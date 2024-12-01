package day01

import Day
import DayTest
import kotlin.math.abs

object DaySolved : Day {

    private var index = 0
    override suspend fun part1(input: List<String>) = input
        .flatMap { text ->
            val (first, second) = text.split("   ")
                .map { it.toInt() }
            listOf(first, second)
        }
        .groupBy { index++ % 2 }
        .let {
            val first = it[0]!!.sortedDescending()
            val second = it[1]!!.sortedDescending()

            first.zip(second) { a, b ->
                abs(a - b)
            }
                .sum()
        }

    override suspend fun part2(input: List<String>) = input
        .flatMap { text ->
            val (first, second) = text.split("   ")
                .map { it.toInt() }
            listOf(first, second)
        }
        .groupBy { index++ % 2 }
        .let {
            val first = it[0]!!
            val second = it[1]!!

            first.sumOf { firstItem ->
                second.count { it == firstItem } * firstItem
            }

        }
}


fun main() {
    val tester = DayTest(DaySolved)
    tester.testAll(11, 936063, 31, 23150395)
}
