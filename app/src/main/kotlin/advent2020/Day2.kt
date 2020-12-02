package advent2020

fun main() {
    val inputs = fileToArr("app/src/main/resources/day2_1.txt")

    val parts = inputs.map { input ->
        input.split(Regex("\\:"))
    }

    val valid1 = part1(parts)
    println(valid1.size)

    val valid2 = part2(parts)
    println(valid2.size)
}

fun part1(parts: List<List<String>>): List<List<String>> {
    return parts.filter { line ->
        val cond = line[0].strip().split(Regex("\\s"))
        val pass = line[1].strip()

        val min = Integer.parseInt(cond[0].split(Regex("\\-"))[0])
        val max = Integer.parseInt(cond[0].split(Regex("\\-"))[1])

        val count = pass.split("").filter { it == cond[1].strip() }.size
        count in min..max
    }
}

fun part2(parts: List<List<String>>): List<List<String>> {
    return parts.filter { line ->
        val cond = line[0].strip().split(Regex("\\s"))
        val pass = line[1].strip()
        val ch = cond[1].strip()

        val at1 = Integer.parseInt(cond[0].split(Regex("\\-"))[0])
        val at2 = Integer.parseInt(cond[0].split(Regex("\\-"))[1])

        val splitted = pass.split("").filter { it != "" }
        (splitted[at1 - 1] == ch || splitted[at2 - 1] == ch) && !(splitted[at1 - 1] == ch && splitted[at2 - 1] == ch)
    }
}