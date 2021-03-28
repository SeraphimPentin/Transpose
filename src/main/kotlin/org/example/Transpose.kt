package org.example

import java.io.File

/**
 * “Транспонирует” входной текст по входящим в него словам,
 * разделённым последовательностями пробелов, т.е.
 * если входящий файл содержал строки “A B C” и “D E”,
 * то результатом будет файл со строками “A D”, “B E” и “C”.
 * file задаёт имя входного файла. Если параметр отсутствует,
 * следует считывать текст с консоли.
 */

fun main(args: Array<String>) {
    TransposeLauncher.main(args)
}

fun parser(flagA: Int, flagT: Boolean, flagR: Boolean, outputFile: String, inputFile: String) {
    val input = File(inputFile.trimIndent()).readLines().map { it.trim() }
    val output = File(outputFile).bufferedWriter()
    val countLine = input.size                     // кол-во сторок, для массива
    val countWords = findMaxLengthLine(inputFile)  // макс. кол-во слов в 1 стр
    val arrayWords = Array(countLine) { Array(countWords) { "x" } }

    for ((numLine, line) in input.withIndex()) {    // кривой алгоритм заполнения массива словами для дальнейшего транспонирования
        var numWord = 0
        val splitStr = line.split(32.toChar())
        for (word in splitStr) {
            if (word == "") continue
            else {
                if (flagT && word.length > flagA) arrayWords[numLine][numWord] = word.substring(0, flagA)
                else {
                    if (flagR) arrayWords[numLine][numWord] = word.padStart(flagA, '_')
                    if (!flagR) arrayWords[numLine][numWord] = word.padEnd(flagA, '_')
                }
            }
            numWord++
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
    for (line in File(inputFile).readLines()) {
        var size = 0
        for (i in line.split(" ")) if (i != "") size++
        if (size > res) res = size
    }
    return res
}

private fun countLine(inputFile: String): Int = File(inputFile).readLines().size
