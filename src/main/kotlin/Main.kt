import kotlin.math.abs

fun main() {
    val a = 3
    val b = 47
    val c = 37
    val d = 90
    val e = 67
    val f = 9990

    val possibleAs = mutableListOf<Int>()
    for (i in 1..f / a) {
        possibleAs.add(i)
    }

    val possibleBs = mutableListOf<Int>()
    for (i in 1..f / b) {
        possibleBs.add(i)
    }

    val possibleCs = mutableListOf<Int>()
    for (i in 1..f / c) {
        possibleCs.add(i)
    }

    val possibleDs = mutableListOf<Int>()
    for (i in 1..f / d) {
        possibleDs.add(i)
    }

    val possibleEs = mutableListOf<Int>()
    for (i in 1..f / e) {
        possibleEs.add(i)
    }

    possibleAs.shuffle()
    possibleBs.shuffle()
    possibleCs.shuffle()
    possibleDs.shuffle()
    possibleEs.shuffle()

    val population = mutableListOf<List<Int>>()
    for (i in 1..100) {
        population.add(listOf(possibleAs[i], possibleBs[i], possibleCs[i], possibleDs[i], possibleEs[i]))
    }

    val descendants = mutableListOf<List<Int>>()
    for (i in 0..<population.size - 1 step 2) {
        descendants.add(listOf(possibleAs[i], possibleBs[i], possibleCs[i], possibleDs[i + 1], possibleEs[i + 1]))
        descendants.add(listOf(possibleAs[i + 1], possibleBs[i + 1], possibleCs[i + 1], possibleDs[i], possibleEs[i]))

        a * possibleAs[i] + b + possibleBs[i] + c * possibleCs[i] + d * possibleDs[i + 1] + e * possibleEs[i + 1]
        a * possibleAs[i + 1] + b * possibleBs[i + 1] + c * possibleCs[i + 1] + d * possibleDs[i] + e * possibleEs[i]
    }

    val sortedDescendants = descendants.sortedBy {
        abs(f - (a * it[0] + b + it[1] + c * it[2] + d * it[3] + e * it[4]))
    }
}