package hu.petert

val input = "R4, R3, R5, L3, L5, R2, L2, R5, L2, R5, R5, R5, R1, R3, L2, L2, L1, R5, L3, R1, L2, R1, L3, L5, L1, R3, L4, R2, R4, L3, L1, R4, L4, R3, L5, L3, R188, R4, L1, R48, L5, R4, R71, R3, L2, R188, L3, R2, L3, R3, L5, L1, R1, L2, L4, L2, R5, L3, R3, R3, R4, L3, L4, R5, L4, L4, R3, R4, L4, R1, L3, L1, L1, R4, R1, L4, R1, L1, L3, R2, L2, R2, L1, R5, R3, R4, L5, R2, R5, L5, R1, R2, L1, L3, R3, R1, R3, L4, R4, L4, L1, R1, L2, L2, L4, R1, L3, R4, L2, R3, L1, L5, R4, R5, R2, R5, R1, R5, R1, R3, L3, L2, L2, L5, R2, L2, R5, R5, L2, R3, L5, R5, L2, R4, R2, L1, R3, L5, R3, R2, R5, L1, R3, L2, R2, R1".split(", ")
val locations = mutableListOf<Location>()
var reachedDestination = false

fun main(args: Array<String>) {
    var direction = 0
    var x = 0
    var y = 0

    for (arg in input) {
        direction = if (arg[0] == 'R') (direction + 1).modulo(4) else (direction - 1).modulo(4)
        val amount = arg.substring(1).toInt()
        if (direction % 2 == 0) {
            val oldY = y
            y += (direction - 1) * amount
            if (y > oldY)
                for (i in oldY + 1..y)
                    checkLocation(Location(x, i))
            else
                for (i in y..oldY - 1)
                    checkLocation(Location(x, i))
        } else {
            val oldX = x
            x += -(direction - 2) * amount
            if (x > oldX)
                for (i in oldX + 1..x)
                    checkLocation(Location(i, y))
            else
                for (i in x..oldX - 1)
                    checkLocation(Location(i, y))
        }
    }
    println("Distance: " + (Math.abs(x) + Math.abs(y)))
}

infix fun Int.modulo(mod: Int): Int {
    val ret = this.mod(mod)
    return if (ret < 0) ret + mod else ret
}

fun checkLocation(location: Location) {
    if (!reachedDestination && locations.contains(location)) {
        reachedDestination = true
        println("Part 2 distance: " + (Math.abs(location.x) + Math.abs(location.y)))
    }
    locations.add(location)
}

data class Location(val x: Int, val y: Int)
