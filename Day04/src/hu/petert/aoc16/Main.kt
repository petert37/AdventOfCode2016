package hu.petert.aoc16

import java.io.File
import java.util.*

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    var sectorSum = 0
    input
            .map(::Room)
            .filter(Room::isReal)
            .forEach {
                sectorSum += it.sector
                println("Name: " + it.decrypt() + " sector: " + it.sector)
            }
    println("Sector sum: " + sectorSum)
}

class Room constructor(val desc: String) {

    private val lastDash = desc.lastIndexOf('-')
    private val firstBracket = desc.indexOf('[')
    val sector = desc.substring(lastDash + 1, firstBracket).toInt()
    val checksum = desc.substring(firstBracket + 1, desc.length - 1)
    val name = desc.substring(0, lastDash)

    fun isReal(): Boolean {
        val letters = HashMap<Char, Int>()
        name
                .filter { it != '-' }
                .forEach {
                    letters.putIfAbsent(it, 0)
                    letters[it] = letters[it]!! + 1
                }

        val sortedLetters = letters.keys.sortedWith(Comparator { l1, l2 ->
            val comp = letters[l2]!!.compareTo(letters[l1]!!)
            if (comp != 0) {
                comp
            } else {
                l1.compareTo(l2)
            }

        })

        var calculatedChecksum = ""
        sortedLetters.forEach { calculatedChecksum += it }
        calculatedChecksum = calculatedChecksum.substring(0, 5)

        return checksum == calculatedChecksum
    }

    fun decrypt(): String {
        var result = ""
        name.forEach { result += rotate(it, sector) }
        return result
    }

    fun rotate(c: Char, amount: Int): Char {
        if (c == '-') return ' '
        return ('a'.toInt() + ((c.toInt() - 'a'.toInt() + amount) % ('z'.toInt() - 'a'.toInt() + 1))).toChar()
    }

}
