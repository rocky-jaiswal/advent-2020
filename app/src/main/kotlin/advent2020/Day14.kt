package advent2020

import kotlin.math.pow

fun main() {
    val inputs = fileToArr("app/src/main/resources/day14_1.txt")

    part1(inputs)
}

private fun toBinaryRep(num: Int): String {
    return Integer.toBinaryString(num).padStart(36, '0')
}

private fun applyMask(bin: String, mask: String): String {
    val foo = mask.split("")
    return bin.split("").withIndex().joinToString("") { ch ->
        if (foo[ch.index] == "X") {
            ch.value
        } else {
            foo[ch.index]
        }
    }
}

private fun toDecimal(finBin: String): Double {
    val init = 0.toDouble()
    return finBin.split("").filter { it != "" }.reversed().withIndex().fold(init) { acc, ch ->
        acc + 2.0.pow(ch.index) * ch.value.toDouble()
    }
}

private fun part1(inputs: List<String>) {
    var mask = ""
    val mem = mutableMapOf<String, Long>()

    inputs.forEach { line ->
        if (line.matches(Regex("^mask(.*)"))) {
            mask = line.split("=").last().strip()
        } else {
            val mat = Regex("^mem\\[(\\d+)] = (\\d+)").matchEntire(line)
            val address = mat!!.groups[1]!!.value
            val num = mat.groups[2]!!.value
            val bin = toBinaryRep(Integer.parseInt(num))
            val fin = applyMask(bin, mask)
            mem.plusAssign(Pair(address, toDecimal(fin).toLong()))
        }
    }

    println(mem.values.sum())
}