package advent2020

fun main() {
    val answers = mutableListOf<String>()
    val inputs = fileToArr("app/src/main/resources/day6_1.txt")

    var d = ""
    inputs.forEach { line ->
        if (line == "" || line == "\n") {
            answers.add(d.strip())
            d = ""
        }
        d = "$d$line|"
    }
    answers.add(d.strip())

//    println(answers)
    println(part1(answers))
    println(part2(answers))
}

private fun part1(answers: MutableList<String>): Int {
    val groups = answers.map{ it.split("|") }.map{ group -> group.filter { it != "" } }
    return groups
            .map{ it.joinToString("").split("").distinct().joinToString("") }
            .map { it.length }
            .sum()
}

private fun part2(answers: MutableList<String>): Int {
    val groups = answers.map{ it.split("|") }.map{ group -> group.filter { it != "" } }
    val all = groups.map m@{ group ->
        if (group.size == 1) {
            return@m group.first().split("").filter { it != "" }.size
        } else {
            val size = group.size
            val ansSize = group
                    .flatMap { ans -> ans.split("").filter { it != "" } }
                    .fold(emptyMap()) { acc: Map<String, Int>, ans: String ->
                        if (acc[ans] != null) {
                            acc.plus(ans to (acc[ans]!! + 1))
                        } else {
                            acc.plus(ans to 1)
                        }
                    }
            return@m ansSize.values.filter { it == size }.size
        }
    }
    return all.sum()
}