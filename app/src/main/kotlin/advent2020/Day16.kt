package advent2020

fun main() {
    val ranges = fileToArr("app/src/main/resources/day16_1.txt")
    val nums = fileToArr("app/src/main/resources/day16_2.txt")

    part1(ranges, nums)
}

private fun part1(inputs: List<String>, nums: List<String>) {
    val ranges = inputs.flatMap { line ->
        val reg = Regex("^(\\w+)\\s?(\\w+)?: (\\d+)-(\\d+) or (\\d+)-(\\d+)")
        val groups = reg.matchEntire(line)!!.groups
        val r1 = Integer.parseInt(groups[3]!!.value)..Integer.parseInt(groups[4]!!.value)
        val r2 = Integer.parseInt(groups[5]!!.value)..Integer.parseInt(groups[6]!!.value)
        listOf(r1, r2)
    }
    val numbers = nums.flatMap { line ->
        line.split(",").filter { it != "" }.map { Integer.parseInt(it) }
    }

    val notIn = numbers.filter { num ->
        !ranges.fold(false) { acc, range ->
            acc || range.contains(num)
        }
    }

    println(notIn.sum())
}