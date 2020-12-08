package advent2020

const val targetSum = 2020.toDouble()

fun main() {
    val inputs = fileToArr("app/src/main/resources/day1_1.txt")

    val nums = inputs.map { input ->
        Integer.parseInt(input).toDouble()
    }

    println(part1(nums, targetSum))
    println(part2(nums, targetSum))
}

private fun part1(nums: List<Double>, targetSum: Double): List<Double?> {
    val soln = nums.find { n ->
        nums.find { it + n == targetSum } != null
    }

    return listOf(soln, targetSum - (soln ?: 0.toDouble()))
}

private fun part2(nums: List<Double>, targetSum: Double): List<Any?> {
    var x: List<Double?> = emptyList()
    val soln = nums.find { n ->
        x = part1(nums, targetSum - n)
        x.first() != null
    }

    return listOf(x, soln)
}