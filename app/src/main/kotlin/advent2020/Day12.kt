package advent2020

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}

private data class ShipPosition(var location: Pair<Int, Int>, var direction: Direction)

fun main() {
    val inputs = fileToArr("app/src/main/resources/day12_1.txt")

    part1(inputs)
}

private fun part1(inputs: List<String>) {
//    println(inputs)
    var ship = listOf(ShipPosition(Pair(0, 0), Direction.EAST))

    fun rotateShip(dir: Direction, step: Int, ins: String): Direction {
        val foo1 = listOf(Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.NORTH)
        val foo2 = listOf(Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH, Direction.EAST, Direction.NORTH, Direction.WEST, Direction.SOUTH)
        return if (ins == "L") {
            val x = foo2.indexOfFirst { it == dir }
            val i = step / 90
            foo2[x+i]
        } else {
            val x = foo1.indexOfFirst { it == dir }
            val i = step / 90
            foo1[x+i]
        }
    }

    fun forward(locn: Pair<Int, Int>, dir: Direction, step: Int): Pair<Int, Int> {
        return when (dir) {
            Direction.NORTH -> Pair(locn.first, locn.second + step)
            Direction.EAST -> Pair(locn.first + step, locn.second)
            Direction.WEST -> Pair(locn.first - step, locn.second)
            Direction.SOUTH -> Pair(locn.first, locn.second - step)
        }
    }

    fun moveShip(pos: String) {
        val dir = Regex("(\\w)(\\d+)").matchEntire(pos)
        val ins = dir!!.groups[1]!!.value
        val step = Integer.parseInt(dir!!.groups[2]!!.value)

        val curr = ship.last()

        when (ins) {
            "N" -> ship = ship.plus(ShipPosition(Pair(curr.location.first, curr.location.second + step), curr.direction))
            "E" -> ship = ship.plus(ShipPosition(Pair(curr.location.first + step, curr.location.second), curr.direction))
            "W" -> ship = ship.plus(ShipPosition(Pair(curr.location.first - step, curr.location.second), curr.direction))
            "S" -> ship = ship.plus(ShipPosition(Pair(curr.location.first, curr.location.second - step), curr.direction))
            "L" -> ship = ship.plus(ShipPosition(Pair(curr.location.first, curr.location.second), rotateShip(curr.direction, step, "L")))
            "R" -> ship = ship.plus(ShipPosition(Pair(curr.location.first, curr.location.second), rotateShip(curr.direction, step, "R")))
            "F" -> ship = ship.plus(ShipPosition(forward(curr.location, curr.direction, step), curr.direction))
        }
    }

    inputs.forEach { pos ->
        moveShip(pos)
    }

    println(ship.last())
}


