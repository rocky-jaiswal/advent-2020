package advent2020

fun main() {
    val inputs2 = "16,12,1,0,15,7,11"
    val inputs = inputs2.split(",").filter { it != "" }.map { Integer.parseInt(it) }

    val init = List<Int?>(2020 - inputs.size){ null }
    val list = inputs.plus(init).withIndex()
    val fin = mutableListOf<Int>()

    val locationMap = mutableMapOf<Int, List<Int>>()

    list.forEach { elem ->
        if (elem.value == null) {
            val num = fin.elementAt(elem.index - 1)
            val occ = locationMap[num]
            if (occ!!.size == 1) {
                val lst1 = if (locationMap[0] == null) emptyList() else locationMap[0]
                locationMap.plusAssign(Pair(0, lst1!!.plus(elem.index)))
                fin.plusAssign(0)
            } else {
                val foo = occ.last() - occ[occ.size - 2]
                val lst2 = if (locationMap[foo] == null) emptyList() else locationMap[foo]
                locationMap.plusAssign(Pair(foo, lst2!!.plus(elem.index)))
                fin.plusAssign(foo)
            }
        } else {
            locationMap.plusAssign(Pair(elem.value!!, listOf(elem.index)))
            fin.plusAssign(elem.value!!)
        }
    }

    println(fin.last())
}