package day10

import Day
import DayTest

private val Long.splitEven: List<Long>
    get() {
        return this.toString().let {
            val middle = it.length / 2
            listOf(it.take(middle).toLong(), it.drop(middle).toLong())
        }
    }
private val Long.digitCountEven: Boolean
    get() {
        return this.toString().length % 2 == 0
    }

object DaySolved : Day {

    override suspend fun part1(input: List<String>) = input[0]
        .split(" ").map(String::toLong)
        .let { numbers ->
            var stones = numbers
            repeat(25) {
                stones = stones.flatMap { engravedNumber ->
                    when {
                        engravedNumber == 0L -> listOf(1L)
                        engravedNumber.digitCountEven -> engravedNumber.splitEven
                        else -> listOf(engravedNumber * 2024L)
                    }
                }
            }

            stones.size
        }

    override suspend fun part2(input: List<String>) = input[0]
        .split(" ").map(String::toLong)
        .let { numbers ->
            val cache = mutableMapOf<Pair<Long, Int>, Long>()

            fun memo(engravedNumber: Long, count: Int): Long {
                if (count == 0) return 1

                return cache.getOrPut(engravedNumber to count) {
                    when {
                        engravedNumber == 0L -> memo(1L, count - 1)
                        engravedNumber.toString().length % 2 == 0 -> engravedNumber.toString()
                            .let { it.chunked(it.length / 2) }
                            .let { (a, b) -> memo(a.toLong(), count - 1) + memo(b.toLong(), count - 1) }

                        else -> memo(engravedNumber * 2024L, count - 1)
                    }
                }
            }
            numbers.sumOf { memo(it, 75) }
        }
}

fun main() {
    val tester = DayTest(DaySolved)
    tester.testAll(55312, 220999, Unit, 261936432123724L)
}
