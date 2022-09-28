class Car(val id: String, val color: String) {
    override fun toString(): String {
        return "$id $color"
    }
}

var lots: MutableList<Car?> = mutableListOf()

fun create(size: Int) {
    lots = MutableList(size) {null}
    println("Created a parking lot with $size spots.")
}

fun park(id: String, color: String) {
    for (i in 0 until lots.size) {
        if (lots[i] == null) {
            lots[i] = Car(id, color)
            println("$color car parked in spot ${i+1}.")
            return
        }
    }
    println("Sorry, the parking lot is full.")
}

fun leave(spot: Int) {
    if (lots[spot-1] != null) {
        lots[spot-1] = null
        println("Spot $spot is free.")
    } else {
        println("There is no car in spot $spot.")
    }
}

fun status() {
    var empty = true
    for (i in 0 until lots.size) {
        if (lots[i] != null) {
            empty = false
            println("${i+1} ${lots[i]}")
        }
    }
    if (empty) {
        println("Parking lot is empty.")
    }
}

fun regByColor(color: String) {
    var result: MutableList<String?> = mutableListOf()
    for (i in 0 until lots.size) {
        if (color.equals(lots[i]?.color, true)) {
            result.add(lots[i]?.id)
        }
    }
    printResult(result, "color $color")
}

fun spotByColor(color: String) {
    var result: MutableList<String?> = mutableListOf()
    for (i in 0 until lots.size) {
        if (color.equals(lots[i]?.color, true)) {
            result.add((i+1).toString())
        }
    }
    printResult(result, "color $color")
}

fun spotByReg(reg: String) {
    var result: MutableList<String?> = mutableListOf()
    for (i in 0 until lots.size) {
        if (reg.equals(lots[i]?.id, true)) {
            result.add((i+1).toString())
        }
    }
    printResult(result, "registration number $reg")
}

fun printResult(result: MutableList<String?>, condition: String) {
    if (result.isNotEmpty()) {
        println(result.joinToString(", "))
    } else {
        println("No cars with $condition were found.")
    }
}

fun doAction(input: MutableList<String>) {
    when (input[0]) {
        "status" -> status()
        "leave" -> leave(input[1].toInt())
        "park" -> park(input[1], input[2])
        "spot_by_color" -> spotByColor(input[1])
        "reg_by_color" -> regByColor(input[1])
        "spot_by_reg" -> spotByReg(input[1])
    }
}

fun main() {
    while (true) {
        val input = readln().split(" ").toMutableList()
        when (input[0]) {
            "exit" -> return
            "create" -> create(input[1].toInt())
            else -> if (lots.isEmpty()) println("Sorry, a parking lot has not been created.") else doAction(input)
        }
    }
}