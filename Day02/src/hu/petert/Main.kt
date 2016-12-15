package hu.petert

import java.io.File

fun main(args: Array<String>) {
    val input = File("input.txt").readLines()
    val keypad = Keypad()
    val crazyKeypad = CrazyKeypad()
    var code = ""
    var crazyCode = ""
    for (line in input) {
        for (c in line) {
            keypad.move(c)
            crazyKeypad.move(c)
        }
        code += keypad.key
        crazyCode += crazyKeypad.keys[crazyKeypad.y][crazyKeypad.x]
    }
    println("Code: " + code)
    println("Crazy Code: " + crazyCode)
}

class Keypad {
    var key = 5

    fun move(dir: Char) {
        when (dir) {
            'U' -> if (key > 3) key -= 3
            'R' -> if (key % 3 != 0) key++
            'D' -> if (key < 7) key += 3
            'L' -> if (key % 3 != 1) key--
        }
    }
}

class CrazyKeypad {
    var keys = Array<Array<Char>>(5, { i -> Array(5, { j -> '0' }) })
    var x = 0
    var y = 2

    init {
        keys[0][2] = '1'
        keys[1][1] = '2'; keys[1][2] = '3'; keys[1][3] = '4'
        keys[2][0] = '5'; keys[2][1] = '6'; keys[2][2] = '7'; keys[2][3] = '8'; keys[2][4] = '9'
        keys[3][1] = 'A'; keys[3][2] = 'B'; keys[3][3] = 'C'
        keys[4][2] = 'D'
    }

    fun move(dir: Char) {
        when (dir) {
            'U' -> if (isValid(x, y - 1)) y--
            'R' -> if (isValid(x + 1, y)) x++
            'D' -> if (isValid(x, y + 1)) y++
            'L' -> if (isValid(x - 1, y)) x--
        }
    }

    fun isValid(x: Int, y: Int): Boolean {
        return !(x < 0 || y < 0 || x > 4 || y > 4 || keys[y][x] == '0')
    }
}