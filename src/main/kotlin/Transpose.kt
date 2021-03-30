import java.io.File


fun main(args: Array<String>) {
    TransposeLauncher.main(args)
}

fun transpose(flagA: Int, flagT: Boolean, flagR: Boolean, outputFile: String, inputFile: String) {
    val input = File(inputFile.trimIndent()).readLines().map { it.trim() }
    val output = File(outputFile).bufferedWriter()
    val countLine = input.size                     // кол-во сторок, для массива
    val countWords = findMaxLengthLine(inputFile)  // макс. кол-во слов в одной стр.
    val arrayWords = Array(countLine) { Array(countWords) { "x" } }

    for ((numLine, line) in input.withIndex()) {    // кривой алгоритм заполнения массива словами для дальнейшего транспонирования
        val splitStr = line.split(32.toChar())
        for ((numWord, word) in splitStr.withIndex()) {
            if (flagT && word.length > flagA) arrayWords[numLine][numWord] = word.substring(0, flagA)
            else {
                if (flagR) arrayWords[numLine][numWord] = word.padStart(flagA, '_')
                if (!flagR) arrayWords[numLine][numWord] = word.padEnd(flagA, '_')
            }
        }
    }

    output.use {
        val transArray = Array(countWords) { Array(countLine) { "x" } }

        for (i in 0 until countWords) {
            for (j in 0 until countLine) {
                transArray[i][j] = arrayWords[j][i]
                if (transArray[i][j] != "x") it.append(transArray[i][j]).append(" ")
                else for (p in 0..flagA) {
                    it.append(" ")
                }
            }
            it.appendLine()
        }
    }
}

private fun findMaxLengthLine(inputFile: String): Int {   // найти строку с наибольшим кол-ом слов, для массива
    var res = 0
    for (line in File(inputFile).readLines().map { it.trim() }) {
        var size = 0
        for (i in line.split(" ")) size++
        if (size > res) res = size
    }
    return res
}

//private fun countLine(inputFile: String): Int = File(inputFile).readLines().size
