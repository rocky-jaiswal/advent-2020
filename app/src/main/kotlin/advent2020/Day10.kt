package advent2020

fun main() {
    val inputs = fileToArr("app/src/main/resources/day10_1.txt")

    part1(inputs)
}

private fun part1(inputs: List<String>) {
    val nums = inputs.map { it.toLong() }.sorted()

    var last = 0L
    val diffs = nums.map { num ->
        val x = num - last
        last = num
        x
    }

    val groups = diffs.plus(3L).groupBy { it }
    println(groups.getOrElse(1) { emptyList() }.size * groups.getOrElse(3) { emptyList() }.size)
}