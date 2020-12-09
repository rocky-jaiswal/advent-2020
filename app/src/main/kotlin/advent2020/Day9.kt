package advent2020

fun main() {
    val inputs = fileToArr("app/src/main/resources/day9_1.txt")

    val res = part1(inputs)
    println(res)

    part2(inputs, res)
}

private fun part1(inputs: List<String>): Long {
    fun hasSum(sum: Long, lst: List<Long>): Boolean {
        return lst.find { n ->
            lst.find { it != n && it + n == sum } != null
        } != null
    }

    val preamble = 25

    val nums = inputs.map { it.toLong() }
    val result = nums.withIndex().first { num ->
        if ((0 until preamble).contains(num.index)) return@first false
        val last = nums.slice((num.index-preamble) until num.index)

        !hasSum(num.value, last)
    }

    return result.value
}

private fun part2(inputs: List<String>, target: Long) {
    val nums = inputs.map { it.toLong() }

    fun findNums(start: Int, end: Int): String {
        val sum = nums.slice(start..end).sum()
        if (sum > target) return "over"
        if (sum == target) return "match"
        return "continue"
    }

    var st = 0
    var endd = st + 1
    var res = findNums(st, endd)
    while (res != "match") {
        if (res == "continue") {
            endd += 1
        }
        if (res == "over") {
            st += 1
            endd = st + 1
        }
        res = findNums(st, endd)
    }

    val arr = nums.slice(st..endd)
    println(arr.min()!! + arr.max()!!)
}


