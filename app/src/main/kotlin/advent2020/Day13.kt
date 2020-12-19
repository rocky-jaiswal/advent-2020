package advent2020

import kotlin.math.min

fun main() {
    val ts = 1002394
    val ids = "13,x,x,41,x,x,x,37,x,x,x,x,x,419,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,19,x,x,x,23,x,x,x,x,x,29,x,421,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,x,17".split(",").filter { it != "x" }.map { Integer.parseInt(it) }

    println(ids)

    val closest = ids.map { id ->
        var x = id
        var mul = 1
        while (x <= ts) {
            x = mul * id
            mul += 1
        }
        x
    }

    val minIdx = closest.withIndex().minBy{ it.value }!!.index
    println(minIdx * ids[minIdx])
}