package advent2020

fun main() {
    val inputs = fileToArr("app/src/main/resources/day9_1.txt")

    part1(inputs)
}

private fun hasSum(sum: Long, lst: List<Long>): Boolean {
    return lst.find { n ->
        lst.find { it != n && it + n == sum } != null
    } != null
}

private fun part1(inputs: List<String>) {
    val preamble = 25

    val nums = inputs.map { it.toLong() }
    val result = nums.withIndex().first { num ->
        if ((0 until preamble).contains(num.index)) return@first false
        val last = nums.slice((num.index-preamble) until num.index)

        !hasSum(num.value, last)
    }

    println(result)
}


