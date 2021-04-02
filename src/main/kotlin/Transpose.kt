import java.io.File
import java.lang.StringBuilder


fun main(args: Array<String>) {
    TransposeLauncher.main(args)
//    transpose(4, flagT = false, flagR = true, "output/exampleOut", "input/example.txt")
}

fun transpose(flagA: Int, flagT: Boolean, flagR: Boolean, outputFile: String, inputFile: String) {
    val input = File(inputFile.trimIndent()).readLines().map { it.trim() }
    val output = File(outputFile).bufferedWriter()
    val countLine = input.size                     // кол-во сторок, для массива
    val countWords = findMaxLengthLine(inputFile)  // макс. кол-во слов в 1 стр
    val arrayWords = Array(countLine) { Array(countWords) { "-" } }


    for ((numLine, line) in input.withIndex()) {    // кривой алгоритм заполнения массива словами для дальнейшего транспонирования
        val splitStr = line.split(" ")
        for ((numWord, word) in splitStr.withIndex()) {
            if (flagT && word.length > flagA) arrayWords[numLine][numWord] = word.substring(0, flagA)
            else {
                if (flagR) arrayWords[numLine][numWord] = word.padStart(flagA, '_')
                if (!flagR) arrayWords[numLine][numWord] = word.padEnd(flagA, '_')
            }
        }
    }

    val sb = StringBuilder()
    val sbF = StringBuilder()
    val transArray = Array(countWords) { Array(countLine) { "-" } }

    for (i in 0 until countWords) {
        for (j in 0 until countLine) {
            transArray[i][j] = arrayWords[j][i]
            if (transArray[i][j] != "-") sb.append(transArray[i][j]).append(" ")
            else for (k in 0..flagA) sb.append(" ")
        }
        if (i != countWords - 1) sb.appendLine()
    }

    if (outputFile == "no") {
        for (line in sb.split(Regex("\\n"))) {
            sbF.append(line.trimEnd()).appendLine()
        }
        println(sbF)
    } else output.use {
        for (line in sb.split(Regex("\\n"))) {
            it.append(line.trimEnd()).appendLine()
        }
    }
    println("the transposition was successful")
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

