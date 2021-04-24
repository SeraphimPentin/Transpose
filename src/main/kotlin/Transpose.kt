import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.IOException

fun main(args: Array<String>) {
    TransposeLauncher.main(args)
    val tl = TransposeLauncher()
    tl.parserArgs(args)

    if(tl.out != null && !File(tl.out).exists()) throw IOException("file not exists")
    val output = if (tl.out == null) System.out.bufferedWriter() else File(tl.out).bufferedWriter()
    val input = if (tl.input == null) System.`in`.bufferedReader() else File(tl.input).bufferedReader()

    transpose(tl.a, tl.t, tl.r, output, input)
    input.close()
    output.close()
}

fun transpose(flagA: Int, flagT: Boolean, flagR: Boolean, output: BufferedWriter, inputFile: BufferedReader) {
    val arrayWords = mutableListOf<List<String>>()
    inputFile.forEachLine { arrayWords.add(it.split(Regex("\\s+"))) }
    val countLine = arrayWords.maxOf { it.size }

    output.use {
        for (i in 0 until countLine) {
            for (j in arrayWords.indices) {
                val len = arrayWords[j].size
                val word = if(len > i ) arrayWords[j][i] else " ".repeat(flagA)
                when {
                    flagT && word.length > flagA -> it.append(word.substring(0, flagA)).append(" ")
                    else -> {
                        if (flagR) it.append(word.padStart(flagA, ' ')).append(" ")
                        if (!flagR) it.append(word.padEnd(flagA, ' ')).append(" ")
                    }
                }
            }
            it.appendLine()
        }
    }
}


