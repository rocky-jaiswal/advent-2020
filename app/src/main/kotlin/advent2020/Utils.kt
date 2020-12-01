package advent2020

import java.io.File

fun fileToArr(filePath: String): List<String> {
    return File(filePath).readLines()
}