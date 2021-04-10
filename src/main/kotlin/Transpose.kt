import java.io.File


fun main(args: Array<String>) {
    TransposeLauncher.main(args)
//    transpose(6, flagT = true, flagR = true, outputFile = "output/exampleOut.txt", inputFile = "input/example.txt")
}

fun transpose(flagA: Int, flagT: Boolean, flagR: Boolean, outputFile: String?, inputFile: String?) {
    val input: List<String?> = if (inputFile == null) listOf(readLine())
    else File(inputFile).readLines().map { it.trim() }

    val output = File(outputFile.toString()).bufferedWriter()
    val countLine = input.size                     // кол-во сторок, для массива
    val countWords = findMaxLengthLine(input)      // макс. кол-во слов в 1 стр
    val arrayWords: Array<Array<String?>> = Array(countWords) { Array(countLine) { null } }


    for ((numLine, line) in input.withIndex()) {    // кривой алгоритм заполнения массива (с учетомв веденных флагов)
        val splitStr = line?.split(" ")              // словами для дальнейшего транспонирования
        if (splitStr != null) {
            for ((numWord, word) in splitStr.withIndex()) {
                when {                                      /* todo */
                    flagT && word.length > flagA -> arrayWords[numWord][numLine] = word.substring(0, flagA)
                    else -> {                               /* todo */
                        if (flagR) arrayWords[numWord][numLine] = word.padStart(flagA, ' ')
                        if (!flagR) arrayWords[numWord][numLine] = word.padEnd(flagA, ' ')
                    }
                }
            }
        }
    }

    if (outputFile == null) {
        for (i in 0 until countWords) {
            for (j in 0 until countLine) {
                if (arrayWords[i][j] != null) print(arrayWords[i][j] + " ")
                else for (k in 0..flagA) print(" ")
            }
            if (i != countWords - 1) println()
        }

    } else output.use {
        for (i in 0 until countWords) {
            for (j in 0 until countLine) {
                if (arrayWords[i][j] != null) it.append(arrayWords[i][j]).append(" ")
                else for (k in 0..flagA) it.append(" ")
            }
            if (i != countWords - 1) it.appendLine()
        }
    }
}

private fun findMaxLengthLine(input: List<String?>): Int {   // найти строку с наибольшим кол-ом слов, для массива
    var res = 0
    for (line in input) {
        val size = line?.split(" ")?.size
        if (size != null) {
            if (size > res) res = size
        }
    }
    return res
}

