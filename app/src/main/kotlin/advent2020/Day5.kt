package advent2020

import kotlin.math.ceil
import kotlin.math.floor

fun main() {
    val inputs = fileToArr("app/src/main/resources/day5_1.txt")

    val p1 = part1(inputs)

    println(p1.max())
    println(part2(p1))
}

fun front(rg: IntRange): IntRange {
    val top = rg.first
    val bottom = rg.last
    return top..floor((top + bottom) / 2.0).toInt()
}

fun back(rg: IntRange): IntRange {
    val top = rg.first
    val bottom = rg.last
    return ceil((top + bottom) / 2.0).toInt()..bottom
}

fun part1(inputs: List<String>): List<Int> {
    return inputs.map { input ->
        val dirs = input.split("").filter { it.strip() != "" }
        var range1 = 0..127
        var range2 = 0..7

        dirs.take(7).forEach { dir ->
            range1 = if (dir == "F") front(range1) else back(range1)
        }
        dirs.takeLast(3).forEach { dir ->
            range2 = if (dir == "L") front(range2) else back(range2)
        }

        val row = range1.first
        val col = range2.first
        row * 8 + col
    }
}

fun part2(seats: List<Int>): List<Int> {
    return (seats.min()!!..seats.max()!!).filter { !seats.contains(it) }
}