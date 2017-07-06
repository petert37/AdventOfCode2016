package hu.petert.aoc16

import java.security.MessageDigest

val input = "wtnhxymk"

fun main(args: Array<String>) {
    var password = ""
    var index = 0

    while (password.length != 8) {
        val hex = hash(input, index++)
        if (hashMatches(hex)) {
            password += hex[5]
        }
    }

    println(password)

    index = 0
    val password2 = mutableListOf<Char>('x', 'x', 'x', 'x', 'x', 'x', 'x', 'x')

    while (password2.contains('x')) {
        val hex = hash(input, index++)
        if (hashMatches(hex)) {
            try {
                val idx = hex[5].toString().toInt()
                if (password2.size > idx && password2[idx] == 'x') {
                    password2[idx] = hex[6]
                }
            } catch (e: NumberFormatException) {
            }
        }
    }

    println(password2)
}

val hashFunction: MessageDigest = MessageDigest.getInstance("MD5")
fun hash(input: String, index: Int) = toHexString(hashFunction.digest((input + index).toByteArray()))

fun hashMatches(hash: String) = hash.matches(Regex("^0{5}.+"))

fun toHexString(bytes: ByteArray): String {
    val hexString = StringBuilder()

    for (i in bytes.indices) {
        val hex = Integer.toHexString(0xFF and bytes[i].toInt())
        if (hex.length == 1) {
            hexString.append('0')
        }
        hexString.append(hex)
    }

    return hexString.toString()
}

