import java.io.BufferedWriter
import java.io.File
import java.io.IOException

fun main(args: Array<String>) {
    TransposeLauncher.main(args)
    val tl = TransposeLauncher()
    tl.parserArgs(args)
    val output = if (tl.out == null) System.out.bufferedWriter() else File(tl.out).bufferedWriter()
    val input = if (tl.input == null) {
        System.`in`.bufferedReader()
    } else File(tl.out).bufferedReader()
    transpose(tl.a, tl.t, tl.r, output, tl.input)  // "input/example.txt"
    output.close()
    input.close()
}

//fun transpose(flagA: Int, flagT: Boolean, flagR: Boolean, output: BufferedWriter, inputFile: String) {
//    val input = if (!File(inputFile).exists() && !File(inputFile).isFile) {
//        while (true) {
//            readLine()
//        }
//    } else File(inputFile).readLines().map { it.trim() }
//    if (inputFile != null && !File(inputFile).isFile && !File(inputFile).exists()) throw IOException()
//    val input: List<String> = if (inputFile == null) listOf(readLine())
//    else File(inputFile).readLines().map { it.trim() }
//    val input = File(inputFile).readLines().map { it.trim() }
//
//    val countLine = input.size                     // кол-во сторок, для массива
//    val countWords = findMaxLengthLine(input)      // макс. кол-во слов в 1 стр
//    val arrayWords: Array<Array<String?>> = Array(countWords) { Array(countLine) { null } }
//
//    for ((numLine, line) in input.withIndex()) {
//        val splitStr = line?.split(" ")
//        if (splitStr != null) {
//            for ((numWord, word) in splitStr.withIndex()) {
//                when {
//                    flagT && word.length > flagA -> arrayWords[numWord][numLine] = word.substring(0, flagA)
//                    else -> {
//                        if (flagR) arrayWords[numWord][numLine] = word.padStart(flagA, ' ')
//                        if (!flagR) arrayWords[numWord][numLine] = word.padEnd(flagA, ' ')
//                    }
//                }
//            }
//        }
//    }
//    System.out.bufferedWriter()
//    if (outputFile == null) {
//        for (i in 0 until countWords) {
//            for (j in 0 until countLine) {
//                if (arrayWords[i][j] != null) print(arrayWords[i][j] + " ")
//                else for (k in 0..flagA) print(" ")
//            }
//            if (i != countWords - 1) println()
//        }
//    } else if (!File(outputFile).isFile && !File(outputFile).exists()) throw IOException() else {
//        val output = File(outputFile.toString())//.bufferedWriter()
//   val out = System.out.bufferedWriter().use {
//            for (i in 0 until countWords) {
//                for (j in 0 until countLine) {
//                    if (arrayWords[i][j] != null) {
//                        it.append(arrayWords[i][j]).append(" ")
//                    } else for (k in 0..flagA) it.append(" ")
//                }
//                if (i != countWords - 1) it.appendLine()
//            }
//        }
//    if(File(outputFile).isFile && File(outputFile).exists()) File(outputFile)
//    }
//}

private fun findMaxLengthLine(input: List<String?>): Int {
    var res = 0
    for (line in input) {
        val size = line?.split(" ")?.size
        if (size != null) {
            if (size > res) res = size
        }
    }
    return res
}

fun transpose(flagA: Int, flagT: Boolean, flagR: Boolean, output: BufferedWriter, inputFile: String) {
    val input = File(inputFile).readLines().map { it.trim() }

    val countLine = input.size
    val countWords = findMaxLengthLine(input)
    val arrayWords: Array<Array<String?>> = Array(countWords) { Array(countLine) { null } }

    for ((numLine, line) in input.withIndex()) {
        val splitStr = line.split(" ")
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

    for (i in 0 until countWords) {
        for (j in 0 until countLine) {
            if (arrayWords[i][j] != null) {
                output.append(arrayWords[i][j]).append(" ")
            } else for (k in 0..flagA) output.append(" ")
        }
        if (i != countWords - 1) output.appendLine()
    }
}





