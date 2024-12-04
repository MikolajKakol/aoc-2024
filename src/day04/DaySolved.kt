package day04

import Day
import DayTest
import util.Direction8Way
import util.Matrix2D
import util.Point2D
import util.println

object DaySolved : Day {

    private val word = "XMAS".toCharArray()

    override suspend fun part1(input: List<String>) =
        Matrix2D.create(input).run {
            walk()
                .sumOf { search(it) }
        }

    private fun Matrix2D.search(p: Point2D): Int {
        return Direction8Way.entries.count { direction ->
            var newP = p
            word.asSequence()
                .all { letter ->
                    (getOrNull(newP) == letter).also {
                        newP = newP.move(direction)
                    }
                }
        }
    }

    private val wordMAS = "MAS".toCharArray()
    private val wordSAM = "SAM".toCharArray()

    override suspend fun part2(input: List<String>) =
        Matrix2D.create(input).run {
            walk()
                .count { searchMas(it) }
        }

    private fun Matrix2D.searchMas(p: Point2D): Boolean {
        val se = Direction8Way.SE.let { direction ->
            var newP = p
            checkWordd(newP, direction)
        }
        val ne = Direction8Way.NE.let { direction ->
            var newP = p.move(Direction8Way.S).move(Direction8Way.S)
            checkWordd(newP, direction)
        }
       return se && ne
    }

    private fun Matrix2D.checkWordd(p: Point2D, direction: Direction8Way): Boolean {
        var newP = p
        return wordMAS.asSequence()
            .also {
                newP = p
            }
            .all { letter ->
                (getOrNull(newP) == letter).also {
                    newP = newP.move(direction)
                }
            } || wordSAM.asSequence()
            .also {
                newP = p
            }
            .all { letter ->
                (getOrNull(newP) == letter).also {
                    newP = newP.move(direction)
                }
            }
    }

}


fun main() {
    val tester = DayTest(DaySolved)
    tester.testAll(18, 2297, 9, 1745)
}
