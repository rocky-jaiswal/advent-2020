package advent2020

fun main() {
    val inputs = fileToArr("app/src/main/resources/day3_1.txt")

    println(plot(inputs, 1, 1))
    println(plot(inputs, 3, 1))
    println(plot(inputs, 5, 1))
    println(plot(inputs, 7, 1))
    println(plot(inputs, 1, 2))
}

private fun plot(inputs: List<String>, right: Int, down: Int): Int {
    val height = inputs.size
    val width = height * (right + 1)

    val forest = mutableMapOf<Pair<Int, Int>, String>()
    for (i in 1..height) {
        for (j in 1..width) {
            val row = inputs[i - 1].split("").filter{ it != ""}
            forest.put(Pair(i, j), row[(j-1) % row.size])
        }
    }

    var isAtEnd = false
    val path = mutableListOf(Pair(1, 1))
    while(!isAtEnd) {
        if (path.last().first >= height) {
            isAtEnd = true
        } else {
            path.add(Pair(path.last().first + down, path.last().second + right))
        }
    }

    return path.filter { forest[it] == "#" }.size
}