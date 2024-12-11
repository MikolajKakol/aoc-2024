package day08

import Day
import DayTest
import util.Matrix2D
import util.Point2D
import util.Vector
import util.println

object DaySolved : Day {

    override suspend fun part1(input: List<String>) = Matrix2D.create(input)
        .let { field ->
            val antenaFrequencies = field.walk()
                .filter { field.get(it) != '.' }
                .map { field.get(it) }
                .toSet()

            val antinodeField = Matrix2D.create(field.width, field.height)

            antenaFrequencies.map { freq ->
                val antenasOnFreq = field.walk()
                    .filter { field.get(it) == freq }
                    .toList()

                antenasOnFreq.permutations()
                    .forEach { (p1, p2) ->
                        val v2 = (p1 - p2)
                        val v1 = v2 * -1

                        putAntinodes(antinodeField, (p2 + v1))
                        putAntinodes(antinodeField, (p1 + v2))
                    }
            }

            antinodeField.walk()
                .filter { antinodeField.get(it) == '#' }
                .count()
        }

    override suspend fun part2(input: List<String>) = Matrix2D.create(input)
        .let { field ->
            val antenaFrequencies = field.walk()
                .filter { field.get(it) != '.' }
                .map { field.get(it) }
                .toSet()

            val antinodeField = Matrix2D.create(field.width, field.height)

            antenaFrequencies.map { freq ->
                val antenasOnFreq = field.walk()
                    .filter { field.get(it) == freq }
                    .toList()

                antenasOnFreq.permutations()
                    .forEach { (p1, p2) ->
                        val v2 = (p1 - p2)
                        val v1 = v2 * -1

                        putAntinodesHarmonis(antinodeField, p2, v1)
                        putAntinodesHarmonis(antinodeField, p1, v2)
                    }
            }

            antinodeField.print()

            antinodeField.walk()
                .filter { antinodeField.get(it) == '#' }
                .count()
        }

    private fun putAntinodesHarmonis(field: Matrix2D, p: Point2D, v: Vector) {
        var curP = p
        while (field.getOrNull(curP) != null) {
            field.set(curP, '#')
            curP += v
        }
    }


    private fun putAntinodes(field: Matrix2D, p: Point2D) {
        field.getOrNull(p)
            ?.apply { field.set(p, '#') }
    }


    private fun <E> List<E>.permutations() = sequence {
        forEachIndexed { index, e ->
            drop(index + 1).forEach { e2 ->
                yield(e to e2)
            }
        }
    }
}


fun main() {
    val tester = DayTest(DaySolved)
    tester.testAll(14, 396, 34, 1)
}
