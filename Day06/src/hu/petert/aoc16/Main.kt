package hu.petert.aoc16

import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    val frequencies = Array(if (input.isEmpty()) 0 else input[0].length, { mutableMapOf<Char, Int>() })

    input.forEach({ it.forEachIndexed { i, c -> frequencies[i].put(c, frequencies[i].getOrDefault(c, 0) + 1) } })

    frequencies.forEach { print((it.maxBy { it.value })?.key) }
    println()
    frequencies.forEach { print((it.minBy { it.value })?.key) }
}

