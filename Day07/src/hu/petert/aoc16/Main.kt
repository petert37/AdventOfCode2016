package hu.petert.aoc16

import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines().map { it.split(Regex("\\[|\\]")) }
    println(input.count { it.isAbba() })
    println(input.count { it.isSSL() })
}

fun String.isAbba() = (0..this.length - 4).any { this[it] != this[it + 1] && this[it] == this[it + 3] && this[it + 1] == this[it + 2] }
fun List<String>.isAbba() = ((0..this.size - 1 step 2).any { i -> this[i].isAbba() } && (1..this.size - 1 step 2).none { i -> this[i].isAbba() })

fun List<String>.isSSL(): Boolean {
    val outs = (0..this.size - 1 step 2).map { this[it] }
    val ins = (1..this.size - 1 step 2).map { this[it] }
    return outs.any { out ->
        (0..out.length - 3).any { i ->
            out[i] == out[i + 2] && out[i] != out[i + 1] && ins.any { inIst ->
                inIst.contains("${out[i + 1]}${out[i]}${out[i + 1]}")
            }
        }
    }
}