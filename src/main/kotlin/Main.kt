import kotlin.math.abs
import kotlin.random.Random

fun main() {
    val a = 1
    val b = 1
    val c = 1
    val d = 1
    val e = 1
    val f = 1000

    var nearestResult = Int.MAX_VALUE
    var resultList = listOf<List<Int>>()

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

    var descendants = mutableListOf<List<Int>>()
    for (i in 0..<population.size - 1 step 2) {
        descendants.add(listOf(possibleAs[i], possibleBs[i], possibleCs[i], possibleDs[i + 1], possibleEs[i + 1]))
        descendants.add(listOf(possibleAs[i + 1], possibleBs[i + 1], possibleCs[i + 1], possibleDs[i], possibleEs[i]))
    }

    var probabilities = descendants.map { element ->
        val value = a * element[0] + b * element[1] + c * element[2] + d * element[3] + e * element[4]
        1 / (1 + abs(f - value).toDouble())
    }.toDoubleArray()

    var selectedIndices = mutableSetOf<Int>()
    val numSelectedElements = 10

    repeat(numSelectedElements) {
        val selectedIndex = rouletteWheelSelection(probabilities)
        selectedIndices.add(selectedIndex)
    }

    var selectedDescendants = selectedIndices.map { descendants[it] }

    for (descendant in selectedDescendants) {
        val value = a * descendant[0] + b * descendant[1] + c * descendant[2] + d * descendant[3] + e * descendant[4]
        if (abs(f - value) < nearestResult) {
            nearestResult = abs(f - value)
            resultList = listOf(descendant)
        }
    }

    var isChange = true
    while (isChange) {
        descendants = mutableListOf()
        for (i in 0..<selectedDescendants.size - 1 step 2) {
            descendants.add(listOf(selectedDescendants[i][0], selectedDescendants[i][1], selectedDescendants[i][2], selectedDescendants[i + 1][3], selectedDescendants[i + 1][4]))
            descendants.add(listOf(selectedDescendants[i + 1][0], selectedDescendants[i + 1][1], selectedDescendants[i + 1][2], selectedDescendants[i][3], selectedDescendants[i][4]))
        }

        probabilities = descendants.map { element ->
            val value = a * element[0] + b * element[1] + c * element[2] + d * element[3] + e * element[4]
            1 / (1 + abs(f - value).toDouble())
        }.toDoubleArray()

        selectedIndices = mutableSetOf()

        repeat(numSelectedElements) {
            val selectedIndex = rouletteWheelSelection(probabilities)
            selectedIndices.add(selectedIndex)
        }

        selectedDescendants = selectedIndices.map { descendants[it] }

        isChange = false
        for (descendant in selectedDescendants) {
            val value = a * descendant[0] + b * descendant[1] + c * descendant[2] + d * descendant[3] + e * descendant[4]
            if (abs(f - value) < nearestResult) {
                isChange = true
                nearestResult = abs(f - value)
                resultList = listOf(descendant)
            }
        }
    }

    println(resultList)
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