package util

import kotlin.math.abs

data class Point2D(val x: Int, val y: Int) : Comparable<Point2D> {

    fun move(direction: Direction, distance: Int = 1): Point2D {
        return when (direction) {
            Direction.NORTH -> Point2D(x, y - distance)
            Direction.SOUTH -> Point2D(x, y + distance)
            Direction.EAST -> Point2D(x + distance, y)
            Direction.WEST -> Point2D(x - distance, y)
        }
    }

    fun move(direction: Direction8Way, distance: Int = 1): Point2D {
        return when (direction) {
            Direction8Way.N -> Point2D(x, y - distance)
            Direction8Way.E -> Point2D(x + distance, y)
            Direction8Way.W -> Point2D(x - distance, y)
            Direction8Way.S -> Point2D(x, y + distance)
            Direction8Way.NE -> Point2D(x + distance, y - distance)
            Direction8Way.SE -> Point2D(x + distance, y + distance)
            Direction8Way.SW -> Point2D(x - distance, y + distance)
            Direction8Way.NW -> Point2D(x - distance, y - distance)
        }
    }

    infix fun with(direction: Direction): DirectedPoint2D {
        return DirectedPoint2D(this, direction)
    }

    fun manhattanDistance(other: Point2D): Int {
        return abs(x - other.x) + abs(y - other.y)
    }

    operator fun minus(other: Point2D): Vector {
        return Vector(x - other.x, y - other.y)
    }

    operator fun plus(other: Vector): Point2D {
        return Point2D(x + other.x, y + other.y)
    }

    override fun compareTo(other: Point2D): Int {
        return when {
            y < other.y -> -1
            y > other.y -> 1
            x < other.x -> -1
            x > other.x -> 1
            else -> 0
        }
    }

    override fun toString(): String {
        return "($x, $y)"
    }
}

data class DirectedPoint2D(val point: Point2D, val direction: Direction) {

    fun move(direction: Direction) = point.move(direction) with direction

    override fun toString(): String {
        return "$point $direction"
    }
}

enum class Direction {
    NORTH, SOUTH, EAST, WEST;

    val isHorizontal: Boolean
        get() = this == EAST || this == WEST

    val isVertical: Boolean
        get() = this == NORTH || this == SOUTH

    val opposite: Direction
        get() = when (this) {
            NORTH -> SOUTH
            SOUTH -> NORTH
            EAST -> WEST
            WEST -> EAST
        }
}

enum class Direction8Way {

    N, NE, E, SE, S, SW, W, NW;

}
