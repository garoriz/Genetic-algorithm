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
}