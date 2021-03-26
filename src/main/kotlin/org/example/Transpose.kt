package org.example

import java.io.File
import java.lang.StringBuilder

/**
 * “Транспонирует” входной текст по входящим в него словам,
 * разделённым последовательностями пробелов, т.е.
 * если входящий файл содержал строки “A B C” и “D E”,
 * то результатом будет файл со строками “A D”, “B E” и “C”.
 * file задаёт имя входного файла. Если параметр отсутствует,
 * следует считывать текст с консоли.
 */

class Transpose {

    fun trans(inputFile: String) {                     // , outputFile: String
        val input = File(inputFile).readLines()
        val countLine = input.size                     // кол-во сторок, для массива
        val countWords = findMaxLengthLine(inputFile)  // макс. кол-во слов в 1 стр
        val arrayWords = Array(countWords) { Array(countLine) { "-" } }
//        val output = File(outputFile).bufferedWriter()

        var numLine = 0
        var numWord = 0

        for (line in input) {    // кривой алгоритм заполнения массива словами для дальнейшего транспонирования
            for (word in line.split(Regex("\\s+"))) {
                arrayWords[numLine][numWord] = word
                numWord++
            }
            numLine++
            numWord = 0
        }

        val transArray = Array(countLine) { Array(countWords) { "-" } }
        val sb = StringBuilder()

        for (i in 0 until countLine){
            for(j in 0 until countWords){
                transArray[i][j] = arrayWords[j][i]
                if(transArray[i][j] != "-") sb.append(transArray[i][j])
            }
            sb.appendln()
        }
    }

    private fun findMaxLengthLine(inputFile: String): Int { // найти строку с наибольшим кол-ом слов, для массива
        var res = 0
        for (line in File(inputFile).readLines()) {
            val currentCountWords = line.split(Regex("\\S+")).size
            if (currentCountWords > res) res = currentCountWords
        }
        return res
    }
}