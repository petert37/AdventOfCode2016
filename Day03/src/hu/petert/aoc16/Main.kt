package hu.petert.aoc16

import java.io.File

var valid = 0
var valid2 = 0

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    for (i in 0 until input.size step 3) {
        val line1 = input[i].split(Regex("\\s+"))
        val line2 = input[i + 1].split(Regex("\\s+"))
        val line3 = input[i + 2].split(Regex("\\s+"))
        if (Triangle(line1[1].toInt(), line2[1].toInt(), line3[1].toInt()).isValid()) valid2++
        if (Triangle(line1[2].toInt(), line2[2].toInt(), line3[2].toInt()).isValid()) valid2++
        if (Triangle(line1[3].toInt(), line2[3].toInt(), line3[3].toInt()).isValid()) valid2++

    }
    input
            .map { it.split(Regex("\\s+")) }
            .filter { Triangle(it[1].toInt(), it[2].toInt(), it[3].toInt()).isValid() }
            .forEach { valid++ }
    println("Valid triangles: " + valid)
    println("Valid triangles2: " + valid2)
}

class Triangle constructor(val s1: Int = 0, val s2: Int = 0, val s3: Int = 0) {
    fun isValid(): Boolean {
        if (s1 + s2 <= s3) return false
        if (s1 + s3 <= s2) return false
        if (s2 + s3 <= s1) return false
        return true
    }
}