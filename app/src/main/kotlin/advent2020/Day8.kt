package advent2020

fun main() {
    val inputs = fileToArr("app/src/main/resources/day8_1.txt")

    val acc1 = part1(inputs)
    println(acc1)
}

private fun part1(inputs: List<String>): Int {
    val visited = mutableSetOf<Int>()
    var acc = 0
    var currLine = 0

    while (!visited.contains(currLine)) {
        visited.add(currLine)
        val line = inputs[currLine]

        val ins = line.split(" ")
        val step = Integer.parseInt(ins[1])

        when (ins[0]) {
            "nop" -> currLine += 1
            "acc" -> {
                acc += step
                currLine += 1
            }
            "jmp" -> currLine += step
        }
    }

    return acc
}

private fun part2(inputs: List<String>): Int {
    throw NotImplementedError()
}