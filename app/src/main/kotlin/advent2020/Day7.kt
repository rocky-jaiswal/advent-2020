package advent2020

fun main() {
    val inputs = fileToArr("app/src/main/resources/day7_1.txt")
    val coll = mutableMapOf<String, List<Pair<String, String>>>()

    inputs.forEach { line ->
        val match = Regex("^(.*) bags contain (.*)\$").matchEntire(line)
        val key = match!!.groups[1]!!.value

        if (match.groups[2]!!.value == "no other bags.") {
            coll[key] = emptyList()
            return@forEach
        }

        val values = match.groups[2]!!.value
                .split(",")
                .map { it.strip() }
                .map { stuff ->
                    val m = Regex("^(\\d+) (.*) bag(.*)\$").matchEntire(stuff)
                    Pair(m!!.groups[1]!!.value, m.groups[2]!!.value)
                }

        coll[key] = values
    }

    println(coll)
    part1(coll)
    part2(coll)
}

fun findContainer(coll: Map<String, List<Pair<String, String>>>, str: String, containers: MutableList<String>): List<String> {
    val canContain = coll.keys.filter { key ->
        coll[key]!!.any { foo -> foo.second == str }
    }

    if (canContain.isNotEmpty()) {
        canContain.flatMap {
            containers.plusAssign(it)
            findContainer(coll, it, containers)
        }
    }

    return containers
}

fun findContents(coll: Map<String, List<Pair<String, String>>>, l: Int, str: String, containers: MutableList<Int>): List<Int> {
    val contents = coll[str]
//    println(contents)

    if (contents != null && contents.isNotEmpty()) {
        contents.flatMap {
            containers.plusAssign(l * Integer.parseInt(it.first))
            findContents(coll, Integer.parseInt(it.first), it.second, containers)
        }
    }

    return containers
}

fun part1(coll: Map<String, List<Pair<String, String>>>) {
    val containers = mutableListOf<String>()
    println(findContainer(coll, "shiny gold", containers).distinct().size)
}

fun part2(coll: Map<String, List<Pair<String, String>>>) {
    val contents = mutableListOf<Int>(1)
    println(findContents(coll, 1, "shiny gold", contents))
//    println(findContents(coll, 1, "shiny gold", contents).sum() - 1)
}