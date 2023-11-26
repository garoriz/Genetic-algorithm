import kotlin.math.abs
import kotlin.random.Random

fun main() {
    val a = 1
    val b = 1
    val c = 1
    val d = 1
    val e = 1
    val f = 1000

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

    val probabilities = descendants.map { element ->
        val value = a * element[0] + b * element[1] + c * element[2] + d * element[3] + e * element[4]
        1 / (1 + abs(f - value).toDouble())
    }.toDoubleArray()

    val selectedIndices = mutableSetOf<Int>()
    val numSelectedElements = 10

    repeat(numSelectedElements) {
        val selectedIndex = rouletteWheelSelection(probabilities)
        selectedIndices.add(selectedIndex)
    }

    val selectedDescendants = selectedIndices.map { descendants[it] }
}

fun rouletteWheelSelection(weights: DoubleArray): Int {
    val totalWeight = weights.sum()
    var randomValue = Random.nextDouble() * totalWeight

    for (i in weights.indices) {
        randomValue -= weights[i]
        if (randomValue <= 0) {
            return i
        }
    }

    return weights.size - 1
}