package util

data class Vector(val x: Int, val y: Int) {

    operator fun times(scale: Int) = Vector(x * scale, y * scale)
}