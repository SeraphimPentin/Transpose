import java.io.File
import java.io.IOException


fun main(args: Array<String>) {
    TransposeLauncher.main(args)
//    transpose(7, flagT = true, flagR = true, outputFile = "output/temp", inputFile = null)
}

fun transpose(flagA: Int, flagT: Boolean, flagR: Boolean, outputFile: String?, inputFile: String?) {
    if (inputFile != null && !File(inputFile).isFile && !File(inputFile).exists()) throw IOException()
    val input: List<String?> = if (inputFile == null) listOf(readLine())
    else File(inputFile).readLines().map { it.trim() }

    val countLine = input.size                     // кол-во сторок, для массива
    val countWords = findMaxLengthLine(input)      // макс. кол-во слов в 1 стр
    val arrayWords: Array<Array<String?>> = Array(countWords) { Array(countLine) { null } }

    for ((numLine, line) in input.withIndex()) {
        val splitStr = line?.split(" ")
        if (splitStr != null) {
            for ((numWord, word) in splitStr.withIndex()) {
                when {
                    flagT && word.length > flagA -> arrayWords[numWord][numLine] = word.substring(0, flagA)
                    else -> {
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
    } else if (!File(outputFile).isFile && !File(outputFile).exists()) throw IOException() else {
        val output = File(outputFile.toString()).bufferedWriter()
        output.use {
            for (i in 0 until countWords) {
                for (j in 0 until countLine) {
                    if (arrayWords[i][j] != null) {
                        it.append(arrayWords[i][j]).append(" ")
/*                    for (f in countLine - 1 downTo j) {
                        if (arrayWords[i][f] != null) {
                            it.append(" ")
                            break
                        } else continue
                    }
 */
                    } else for (k in 0..flagA) it.append(" ")
                }
                if (i != countWords - 1) it.appendLine()
            }
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

