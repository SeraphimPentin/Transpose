import org.junit.Test
import java.io.File
import kotlin.test.assertFalse
import kotlin.test.assertTrue
import org.junit.jupiter.api.Assertions.assertThrows
import java.io.IOException

class TransposeKtTest {

    private fun assertFileContent(expectedFile: String, actualFile: String): Boolean {
        val expected = File(expectedFile).readLines()
        val actual = File(actualFile).readLines()
        return expected == actual
    }

    @Test
    fun transpose() {
        main("-a 6 -t -r -o output/exampleOut.txt input/example.txt".split(" ").toTypedArray())
        assertTrue { assertFileContent("output/expectedOut", "output/exampleOut.txt") }
        assertFalse { assertFileContent("input/example_1", "input/example_2") }
    }

    @Test
    fun transpose1() {
        main("-t -r -o output/exampleOut_1 input/example_1".split(" ").toTypedArray())
        assertTrue { assertFileContent("output/expectedOut_1", "output/exampleOut_1") }
        assertFalse { assertFileContent("output/expectedOut_1", "input/example_1") }
    }

    @Test
    fun exceptionTest() {
        assertThrows(IOException::class.java) {
            main(toString().split(" ").toTypedArray())
        }
    }

    @Test
    fun exceptionTest1() {
        assertThrows(IOException::class.java) {
            main("-t -r ddfffd.txt".split(" ").toTypedArray())
        }
    }

    @Test
    fun exceptionTest2() {
        assertThrows(IOException::class.java) {
            main("transpose -r -o output/exampleOut_1".split(" ").toTypedArray())
        }
    }

    @Test
    fun exceptionTest3() {
        assertThrows(IOException::class.java) {
            main("-a 4 -t -o KEK input/example_1".split(" ").toTypedArray())
        }
    }

    @Test
    fun consoleOutput() {
        main("-a 5 -t input/example_1".split(" ").toTypedArray())
    }

    @Test
    fun consoleInput() {
        main("-a 5 -t -o output/temp input/example_2".split(" ").toTypedArray())
    }
    @Test
    fun invalidArg(){
        main("-in -id".split(" ").toTypedArray())
    }
}