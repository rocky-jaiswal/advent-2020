package advent2020

val validFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid", "cid")
val mandatoryFields = validFields - "cid"

data class RawPerson(val desc: String)

fun main() {
    val people = mutableListOf<RawPerson>()
    val inputs = fileToArr("app/src/main/resources/day4_1.txt")

    var d = ""
    inputs.forEach { line ->
        if (line == "" || line == "\n") {
            people.add(RawPerson(d.strip()))
            d = ""
        }
        d = "$d$line "
    }
    people.add(RawPerson(d.strip()))

    val validPass = part1(people)
    println(validPass.size)

    val realPass = part2(validPass)
    println(realPass.size)
}

fun part1(people: List<RawPerson>): List<RawPerson> {
    return people.filter { rawPerson ->
        val rpd = rawPerson.desc.split(Regex("\\s"))
        val traits = rpd.map { it.split(":").first() }
        traits.containsAll(mandatoryFields)
    }
}

fun part2(people: List<RawPerson>): List<List<Map<String, String>>> {
    val peopleWithTraits = people.map { rawPerson ->
        val rpd = rawPerson.desc.split(Regex("\\s"))
        rpd.map { it.split(":") }.map{ mapOf(Pair(it[0], it[1])) }
    }

    return peopleWithTraits.filter { pwt ->
        val personMap = pwt.reduce { acc, entry ->
            acc.plus(entry.keys.first() to entry.values.first())
        }

        val hgt = personMap["hgt"]
        val okHeight = hgt != null && (hgt.endsWith("cm") || hgt.endsWith("in"))

        val allOkHeight = if (okHeight) {
            if (hgt!!.endsWith("cm")) {
                (150..193).contains(Integer.parseInt(hgt.substring(0, hgt.length - 2)))
            } else {
                (59..76).contains(Integer.parseInt(hgt.substring(0, hgt.length - 2)))
            }
        } else false

        allOkHeight &&
                (1920..2002).contains(Integer.parseInt(personMap["byr"])) &&
                (2010..2020).contains(Integer.parseInt(personMap["iyr"])) &&
                (2020..2030).contains(Integer.parseInt(personMap["eyr"])) &&
                listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth").contains(personMap["ecl"]) &&
                personMap["pid"]!!.length == 9 &&
                personMap["hcl"]!!.matches(Regex("#[a-f0-9]{6}"))
    }
}