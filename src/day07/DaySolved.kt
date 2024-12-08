package day07

import Day
import DayTest
import util.println
import kotlin.math.pow
import kotlin.math.roundToInt

private typealias Operation = Pair<List<Long>, List<DaySolved.Operator>>

object DaySolved : Day {


    override suspend fun part1(input: List<String>) = input
        .map { line ->
            val (result, parts) = line.split(": ")
                .let { it[0].toLong() to it[1].split(" ").map(String::toLong) }

            val cache: MutableMap<Operation, Long> = mutableMapOf(
                (listOf(parts[0]) to emptyList<Operator>()) to parts[0]
            )

            val found = sequence<Operation> {
                (2..parts.size)
                    .map(parts::take)
                    .onEach { numbers ->
                        operators(numbers.size - 1)
                            .onEach { yield(numbers to it) }
                    }
            }
                .onEach { (numbers, operations) ->
                    val cacheVal = requireNotNull(cache[numbers.dropLast(1) to operations.dropLast(1)]) {
                        "cache was empty"
                    }

                    cache[numbers to operations] = when (operations.last()) {
                        Operator.ADD -> cacheVal + numbers.last()
                        Operator.MUL -> cacheVal * numbers.last()
                        Operator.CON -> (cacheVal.toString() + numbers.last().toString()).toLong()
                    }
                }
                .filter { it.first == parts }
                .any { operation ->
                    cache[operation] == result
                }
            if (found) result else 0L
        }
        .sum()

    override suspend fun part2(input: List<String>) = input
        .map { line ->
            val (result, parts) = line.split(": ")
                .let { it[0].toLong() to it[1].split(" ").map(String::toLong) }

            val cache: MutableMap<Operation, Long> = mutableMapOf(
                (listOf(parts[0]) to emptyList<Operator>()) to parts[0]
            )

            val found = sequence<Operation> {
                (2..parts.size)
                    .map(parts::take)
                    .onEach { numbers ->
                        operators2(numbers.size - 1)
                            .onEach { yield(numbers to it) }
                    }
            }
                .onEach { (numbers, operations) ->
                    val cacheVal = requireNotNull(cache[numbers.dropLast(1) to operations.dropLast(1)]) {
                        "cache was empty"
                    }

                    cache[numbers to operations] = when (operations.last()) {
                        Operator.ADD -> cacheVal + numbers.last()
                        Operator.MUL -> cacheVal * numbers.last()
                        Operator.CON -> (cacheVal.toString() + numbers.last().toString()).toLong()
                    }
                }
                .filter { it.first == parts }
                .any { operation ->
                    cache[operation] == result
                }
            if (found) result else 0L
        }
        .sum()

    private fun operators(depth: Int) = 2.toFloat().pow(depth)
        .roundToInt()
        .let { it - 1 }
        .let { (0..it) }
        .map {
            it.toString(2).padStart(depth, '0')
                .map { if (it == '1') Operator.ADD else Operator.MUL }
        }

    private fun operators2(depth: Int) = 3.toFloat().pow(depth)
        .roundToInt()
        .let { it - 1 }
        .let { (0..it) }
        .map {
            it.toString(3).padStart(depth, '0')
                .map {
                    when (it) {
                        '1' -> Operator.ADD
                        '0' -> Operator.CON
                        else -> Operator.MUL
                    }
                }
        }

    enum class Operator {
        ADD, MUL, CON;
    }
}


fun main() {
    val tester = DayTest(DaySolved)
    tester.testAll(3749L, 1038838357795L, 11387L, 254136560217241L)
}
