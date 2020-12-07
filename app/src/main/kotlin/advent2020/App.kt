package advent2020

class App {

    companion object {
        private const val greeting = "Hello Advent!"
    }

    val greeting: String
        get() {
            return Companion.greeting
        }
}

fun main() {
    println(App().greeting)
}
