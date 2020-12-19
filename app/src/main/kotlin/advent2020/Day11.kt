package advent2020

private data class Position(val location: Pair<Int, Int>, var history: List<String>)

fun main() {
    val inputs = fileToArr("app/src/main/resources/day11_1.txt")

    part1(inputs)
}

private fun getSurrounding(location: Pair<Int, Int>): List<Pair<Int, Int>> {
    return listOf(  Pair(location.first-1, location.second-1),
                    Pair(location.first-1, location.second),
                    Pair(location.first-1, location.second+1),
                    Pair(location.first, location.second-1),
                    Pair(location.first, location.second+1),
                    Pair(location.first+1, location.second-1),
                    Pair(location.first+1, location.second),
                    Pair(location.first+1, location.second+1)
            )
}

private fun prettyPrint(positions: MutableList<Position>) {
    var change = 0
    positions.forEach { pos ->
        if (pos.location.first != change) {
            change = pos.location.first
            println("")
        }
        print(pos.history.last())
    }
    println("")
    println("===========================")
}

private fun doesChange(positions: MutableList<Position>): Boolean {
    if (positions.first().history.size == 1) {
        return true
    }

    val last = positions.joinToString("") { pos ->
        pos.history.last()
    }
    val secondLast = positions.joinToString("") { pos ->
        pos.history[pos.history.size - 2]
    }
    return last != secondLast
}

private fun part1(inputs: List<String>) {
    val positions = mutableListOf<Position>()

    inputs.withIndex().forEach { line ->
        val pos = line.value.split("").filter { it != "" }
        pos.withIndex().forEach { ch ->
            positions.plusAssign(Position(Pair(line.index, ch.index), listOf(ch.value.strip())))
        }
    }
//    prettyPrint(positions)

    var run = 0
    while (doesChange(positions)) {
        positions.forEach { pos ->
            val surrounding = getSurrounding(pos.location)
            val neighbours = positions.filter { surrounding.contains(it.location) }

            if (pos.history[run] == "L") {
                val allEmpty = neighbours.map { it.history[run] }.all { it == "L" || it == "." }
                if (allEmpty) {
                    pos.history = pos.history.plus("#")
                } else {
                    pos.history = pos.history.plus("L")
                }
            }
            if (pos.history[run] == "#") {
                val occ = neighbours.map { it.history[run] }.filter { it == "#" }
                if (occ.size > 3) {
                    pos.history = pos.history.plus("L")
                } else {
                    pos.history = pos.history.plus("#")
                }
            }
            if (pos.history[run] == ".") {
                pos.history = pos.history.plus(".")
            }
        }
//        prettyPrint(positions)
        run += 1
    }

    val occupied = positions.map { it.history.last() }.filter { it == "#" }.size
    println(occupied)
}




